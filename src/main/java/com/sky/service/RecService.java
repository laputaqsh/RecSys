package com.sky.service;

import com.sky.dao.Model;
import com.sky.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class RecService {

    private UserRepos userRepos;
    private GroupRepos groupRepos;
    private EventRepos eventRepos;
    private GroupUserRepos groupUserRepos;
    private TrainSetRepos trainSetRepos;
    private TestSetRepos testSetRepos;
    private RecResRepos recResRepos;

    @Autowired
    public void setUserRepos(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Autowired
    public void setEventRepos(EventRepos eventRepos) {
        this.eventRepos = eventRepos;
    }

    @Autowired
    public void setGroupRepos(GroupRepos groupRepos) {
        this.groupRepos = groupRepos;
    }

    @Autowired
    public void setGroupUserRepos(GroupUserRepos groupUserRepos) {
        this.groupUserRepos = groupUserRepos;
    }

    @Autowired
    public void setTrainSetRepos(TrainSetRepos trainSetRepos) {
        this.trainSetRepos = trainSetRepos;
    }

    @Autowired
    public void setTestSetRepos(TestSetRepos testSetRepos) {
        this.testSetRepos = testSetRepos;
    }

    @Autowired
    public void setRecResRepos(RecResRepos recResRepos) {
        this.recResRepos = recResRepos;
    }

    private Model init(int num) {
        System.out.println("init model...");
        int U = userRepos.counts();
        int V = eventRepos.counts();
        int G = groupRepos.counts();
        Model model = new Model(U, V, G);
        for (int g = 0; g < model.G; g++) {
            model.trainset[g] = new ArrayList<>(trainSetRepos.findByGroupId(g));
            model.testset[g] = new ArrayList<>(testSetRepos.findByGroupId(g));
            model.groups[g] = new ArrayList<>(groupUserRepos.findByGroupId(g));
        }

        Random rand = new Random();
        for (int g = 0; g < model.trainset.length; g++) {
            model.Zg[g] = rand.nextInt(model.Z);
            model.Cg[g] = rand.nextInt(model.C);
            model.Rg[g] = rand.nextInt(model.R);
            for (int v : model.trainset[g]) {
                model.paramsIteration(1, g, v, model.Zg[g], model.Cg[g], model.Rg[g]);
            }
        }

        model.generate(num);
        return model;
    }

    private int[][] recommend(Model model, int topn) {
        System.out.println("making rec...");
        List<Integer> candlist = new ArrayList<>(testSetRepos.lists());
        double[][] scores = new double[model.G][candlist.size()];
        int[][] reclist = new int[model.G][topn];
        double su, sr, s;
        for (int g = 0; g < model.testset.length; g++) {
            for (int vi = 0; vi < candlist.size(); vi++) {
                int v = candlist.get(vi);
                s = 1;
                for (int z = 0; z < model.Z; z++) {
                    su = 0;
                    for (int u : model.groups[g]) {
                        su += model.gz[g][z] * model.zu[z][u] * (model.lambda[u][0] * model.zv[z][v] + (1 - model.lambda[u][0]) * model.uv[u][v]);
                    }
                    s *= su;
                    sr = 0;
                    for (int r = 0; r < model.R; r++) {
                        sr += model.zr[z][r] * model.rv[r][v] * 100000;
                    }
                    s *= sr;
                }
                scores[g][vi] = s * model.pm(v);
            }
        }

        for (int g = 0; g < model.G; g++) {
            int[] events = model.descSort(candlist, scores[g]);
            for (int v = 0; v < topn; v++) {
                System.arraycopy(events, 0, reclist[g], 0, topn);
            }
        }
        log.info("rec finished.");
        return reclist;
    }

    public void saveRecRes() {
        int itemNum = 20, topn = 20;
        Model model = init(itemNum);
        int[][] recs = recommend(model, topn);
        for (int g = 0; g < model.G; g++) {
            for (int v : recs[g]) {
                recResRepos.insert(g, v);
            }
        }
    }
}
