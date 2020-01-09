package com.laputa.service;

import com.laputa.constant.CookieConstant;
import com.laputa.constant.RedisConstant;
import com.laputa.dao.*;
import com.laputa.exception.AuthorizeException;
import com.laputa.rec.MY;
import com.laputa.utils.CookieUtil;
import com.laputa.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@Service
@Slf4j
public class RecService {

    @Autowired
    private RecRepos recRepos;

    @Autowired
    private EventRepos eventRepos;

    private Map<Integer, List<Integer>> groupMap;
    private Map<Integer, Integer> emap;
    private Map<Integer, Integer> umap;

    public RecService() {
        groupMap = new HashMap<>();
        File file = new File("dataset/douban/g_map.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int gidx = Integer.parseInt(tmp[0]);
                int uidx = Integer.parseInt(tmp[1]);
                List<Integer> list = groupMap.getOrDefault(uidx, new ArrayList<>());
                list.add(gidx);
                groupMap.put(uidx, list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getRecs(int userId) {
        int uidx = getVal(userId);
        List<Integer> gidxs = groupMap.get(uidx);
        int gidx = gidxs.get(0);

        Set<Integer> eidxs = recRepos.findByGroupId(gidx);
        List<Integer> eids = getList(eidxs);

        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 10) {
            set.add(random.nextInt(eids.size()));
        }
        List<Event> recList = new ArrayList<>();
        for (int i : set) {
            int id = eids.get(i);
            Event event = eventRepos.findById(id);
            recList.add(event);
        }

        return recList;
    }

    public void setRecs() {
        Set<Integer>[] recs = MY.getRecs(200);
        for (int gidx = 0; gidx < recs.length; gidx++) {
            int gid = getKey(gidx);
            for (int eid : getList(recs[gidx])) {
                recRepos.insert(gid, eid);
            }
        }
    }

    private int getVal(int id) {
        File file = new File("dataset/douban/u_map.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int key = Integer.parseInt(tmp[0]);
                int val = Integer.parseInt(tmp[1]);
                if (key == id)
                    return val;
            }
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int getKey(int id) {
        File file = new File("dataset/douban/g_map.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int key = Integer.parseInt(tmp[0]);
                int val = Integer.parseInt(tmp[1]);
                if (val == id)
                    return key;
            }
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private List<Integer> getList(Set<Integer> idx) {
        List<Integer> res = new ArrayList<>();
        File file = new File("dataset/douban/e_map.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int key = Integer.parseInt(tmp[0]);
                int val = Integer.parseInt(tmp[1]);
                if (idx.contains(val)) {
                    res.add(key);
                }
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
