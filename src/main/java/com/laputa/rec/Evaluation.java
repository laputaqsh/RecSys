package com.laputa.rec;

import java.util.ArrayList;
import java.util.HashSet;

class Evaluation {

    static void evaluate(String name, ArrayList<Integer>[] testset, int[][] reclist, int topn) {
        int hit = 0, sum = 0;
        for (int g = 0; g < testset.length; g++) {
            HashSet<Integer> set = new HashSet<>(testset[g]);
            sum += set.size();
            for (int v : reclist[g]) {
                if (set.contains(v))  {
                    hit++;
                    //System.out.println("g: " + g + ", v: " + v);
                }
            }
        }
        double p = 1.0 * hit / (Input.g_num * topn);
        double r = 1.0 * hit / sum;
        double f1 = 1.0 * (2 * p * r) / (p + r);
        Dataset.saveEvaluation(name, topn, hit, sum, p, r, f1);
    }
}
