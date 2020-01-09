package com.laputa.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;
import com.laputa.dao.*;
import com.laputa.repos.EventRepos;
import com.laputa.repos.EventUserRepos;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EventServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventUserRepos eventUserRepos;

    @Autowired
    private EventRepos eventRepos;

    private Map<Integer, Integer> getMap(String fileName) {
        Map<Integer, Integer> map = new HashMap<>();
        File file = new File("dataset/douban/" + fileName + ".csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int id = Integer.parseInt(tmp[0]);
                int idx = Integer.parseInt(tmp[1]);
                map.put(id, idx);
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Integer>[] getEUList() {
        int g_num = 179;
        int e_num = 2686;
        int u_num = 40949;
        Map<Integer, Integer> emap = getMap("e_map");
        Map<Integer, Integer> umap = getMap("u_map");

        List<Integer>[] events = new ArrayList[e_num];
        File file = new File("dataset/douban/events.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int eidx = Integer.parseInt(tmp[0]);
                int uidx = Integer.parseInt(tmp[1]);
                if (events[eidx] == null) {
                    events[eidx] = new ArrayList<>();
                }
                events[eidx].add(uidx);
            }
            return events;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void setMap() {
        Map<Integer, Integer> map = new HashMap<>();
        File file = new File("dataset/douban/g_map.csv");

        int idx = 0;
        String line;
        List<Event> eventList = eventService.lists(0, eventService.counts());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Event event : eventList) {
                int id = event.getOwnerId();
                if (map.containsKey(id)) {
                    continue;
                }
                map.put(id, idx++);
                line = id + "," + map.get(id) + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setEvents() {
        Map<Integer, Integer> emap = getMap("e_map");
        Map<Integer, Integer> umap = getMap("u_map");
        File file = new File("dataset/douban/events.csv");

        List<EventUser> eventUsers = eventUserRepos.lists();

        String line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (EventUser item : eventUsers) {
                int eidx = emap.get(item.getEventId());
                int uidx = umap.get(item.getUserId());
                line = eidx + "," + uidx + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void func() {
        Set<Integer> set = new HashSet<>();
        List<Event> eventList = eventService.lists(0, eventService.counts());
        for (Event event : eventList) {
            set.add(event.getOwnerId());
        }
        System.out.println(set.size());
    }

    @Test
    public void update() throws IOException {
        int g_num = 179;
        int e_num = 2686;
        int u_num = 40949;
        Map<Integer, Integer> emap = getMap("e_map");
        Map<Integer, Integer> gmap = getMap("g_map");
        List<Integer>[] euList = getEUList();

        File file = new File("dataset/douban/groups.csv");

        List<Event> eventList = eventService.lists(0, eventService.counts());
        List<Integer>[] groupEvents = new ArrayList[g_num];
        for (Event event : eventList) {
            int gidx = gmap.get(event.getOwnerId());
            int eidx = emap.get(event.getId());
            if (groupEvents[gidx] == null) {
                groupEvents[gidx] = new ArrayList<>();
            }
            groupEvents[gidx].add(eidx);
        }

        String line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int gidx = 0; gidx < g_num; gidx++) {
                for (Integer eidx : groupEvents[gidx]) {
                    for (Integer uidx : euList[eidx]) {
                        line = gidx + "," + uidx + "\n";
                        writer.write(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setTrainAndTestSet() throws IOException {
        int g_num = 179;
        int e_num = 2686;
        int u_num = 40949;
        Map<Integer, Integer> emap = getMap("e_map");
        Map<Integer, Integer> gmap = getMap("g_map");
        List<Integer>[] euList = getEUList();

        File trainFile = new File("dataset/douban/train.csv");
        File testFile = new File("dataset/douban/test.csv");

        List<Event> eventList = eventService.lists(0, eventService.counts());
        List<Event>[] groupEvents = new ArrayList[g_num];
        for (Event event : eventList) {
            int gidx = gmap.get(event.getOwnerId());
            if (groupEvents[gidx] == null) {
                groupEvents[gidx] = new ArrayList<>();
            }
            groupEvents[gidx].add(event);
        }

        try (BufferedWriter train = new BufferedWriter(new FileWriter(trainFile));
                BufferedWriter test = new BufferedWriter(new FileWriter(testFile))) {
            for (int gidx = 0; gidx < groupEvents.length; gidx++) {
                List<Event> events = groupEvents[gidx];

                events.sort(Comparator.comparing(e -> e.getBeginTime()));

                int testNum = (events.size() + 3) / 5;
                int trainNum = events.size() - testNum;
                String line;
                int idx = 0;

                for (Event event : events) {
                    int eidx = emap.get(event.getId());
                    line = gidx + "," + eidx + "\n";
                    if (idx < trainNum) {
                        train.write(line);
                    } else {
                        test.write(line);
                    }
                    idx++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
