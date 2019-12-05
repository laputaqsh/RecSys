package com.sky.service;

import com.sky.repos.*;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class DataSetService {

    private TrainSetRepos trainSetRepos;
    private TestSetRepos testSetRepos;
    private GroupRepos groupRepos;
    private UserRepos userRepos;
    private EventRepos eventRepos;
    private RegionRepos regionRepos;
    private GroupUserRepos groupUserRepos;
    private RecResRepos recResRepos;

    @Autowired
    public void setGroupUserRepos(GroupUserRepos groupUserRepos) {
        this.groupUserRepos = groupUserRepos;
    }

    @Autowired
    public void setTrainSetRepos(TrainSetRepos trainSetRepos) {
        this.trainSetRepos = trainSetRepos;
    }

    @Autowired
    public void setRecResRepos(RecResRepos recResRepos) {
        this.recResRepos = recResRepos;
    }

    @Autowired
    public void setTestSetRepos(TestSetRepos testSetRepos) {
        this.testSetRepos = testSetRepos;
    }

    @Autowired
    public void setGroupRepos(GroupRepos groupRepos) {
        this.groupRepos = groupRepos;
    }

    @Autowired
    public void setUserRepos(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Autowired
    public void setEventRepos(EventRepos eventRepos) {
        this.eventRepos = eventRepos;
    }

    @Autowired
    public void setRegionRepos(RegionRepos regionRepos) {
        this.regionRepos = regionRepos;
    }

    public void writeDataSet() {
        /*String prefix = "D:/Documents/MyProjects/JavaProjects/RecAlg/dataset/meetup/";
        String trainFile = "train.csv";
        String testFile = "test.csv";
        String groupFile = "groups.csv";
        String eventFile = "events.csv";
        String regionFile = "locations.csv";

        ArrayList<Integer>[] groups = readTrainOrTestOrGroup(prefix + eventFile);
        assert groups != null;
        for (int g = 0; g < groups.length; g++) {
            for (int u : groups[g]) {
                groupUserRepos.insert(g, u);
            }
        }*/

        List<Integer> set = recResRepos.lists();
        set.sort(Comparator.naturalOrder());
        log.info("Num: " + set.size());
        for (int e : set) {
            log.info("  " + e);
        }

    }

    private String[][] readRegion(String fileName) {
        String[][] regions = new String[1053][2];
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tmp = line.split(",");
                int lid = Integer.parseInt(tmp[0]);
                regions[lid][0] = tmp[1];
                regions[lid][1] = tmp[2];
            }
            return regions;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int[] readEvent(String fileName) {
        int[] events = new int[1751];
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                int eid = Integer.parseInt(temp[0]);
                int lid = Integer.parseInt(temp[1]);
                events[eid] = lid;
            }
            return events;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Integer>[] readTrainOrTestOrGroup(String fileName) {
        ArrayList<Integer>[] groups = new ArrayList[20];
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                int gid = Integer.parseInt(temp[0]);
                int uid = Integer.parseInt(temp[1]);
                if (groups[gid] == null) {
                    groups[gid] = new ArrayList<>();
                }
                groups[gid].add(uid);
            }
            return groups;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
