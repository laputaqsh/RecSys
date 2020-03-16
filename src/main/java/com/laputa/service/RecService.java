package com.laputa.service;

import com.laputa.dao.*;
import com.laputa.rec.PMGR;
import com.laputa.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@Slf4j
public class RecService {

    @Autowired
    private RecRepos recRepos;

    @Autowired
    private EventRepos eventRepos;

    private int getGroupId(int userId) {
        int uidx = getVal(userId);
        int gidx = 285988;//默认
        File file = new File("dataset/douban/groups.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int id = Integer.parseInt(tmp[0]);
                int idx = Integer.parseInt(tmp[1]);
                if (idx == uidx) {
                    gidx = id;
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getKey(gidx);
    }

    public List<Event> getRecs(int userId) {
        int groupId = getGroupId(userId);
        List<Integer> recs = recRepos.findByGroupId(groupId);
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 10) {
            set.add(random.nextInt(recs.size()));
        }
        List<Event> recList = new ArrayList<>();
        for (int i : set) {
            int id = recs.get(i);
            Event event = eventRepos.findById(id);
            recList.add(event);
        }
        return recList;
    }

    public void setRecs() {
        Set<Integer>[] recs = PMGR.getRecs(200);
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
