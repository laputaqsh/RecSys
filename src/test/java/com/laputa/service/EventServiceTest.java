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

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EventServiceTest {


    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    private Map<Integer, Integer> getMap(String fileName) {
        Map<Integer, Integer> map = new HashMap<>();
        File file = new File("dataset/douban/"+fileName+"_map.csv");
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

    @Test
    public void update() {
        int g_num = 1379;
        Map<Integer, Integer> emap = getMap("e");
        Map<Integer, Integer> gmap = getMap("g");
        Map<Integer, Integer> umap = getMap("u");

        File trainFile = new File("");

        List<Event> eventList = eventService.lists(0, eventService.counts());
        List<Event>[] groupEvents = new ArrayList[g_num];
        for (Event event : eventList) {
            int gid = event.getOwnerId();
            if (groupEvents[gid] == null) {
                groupEvents[gid] = new ArrayList<>();
            }
            groupEvents[gid].add(event);
        }

        for (List<Event> events : groupEvents) {
            events.sort(Comparator.comparing(e -> e.getBeginTime()));

            int testNum = events.size() / 5;
            int trainNum = events.size() - testNum;
            int idx = 0;

            try (BufferedReader train = new BufferedReader(new FileReader(Input.trainfile)); BufferedReader test = new BufferedReader(new FileReader(Input.testfile))) {
                String line;
                train.readLine();
                while ((line = train.readLine()) != null) {
                    String[] tmp = line.split(",");
                    trSet.add(Integer.parseInt(tmp[1]));
                }
                test.readLine();
                while ((line = test.readLine()) != null) {
                    String[] tmp = line.split(",");
                    teSet.add(Integer.parseInt(tmp[1]));
                }
                System.out.println(trSet.size());
                System.out.println(teSet.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
