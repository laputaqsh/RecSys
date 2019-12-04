package com.sky.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    public int U, V, G, Z, C, R;
    public double alpha, beta, eta, eplisilon, gamma, gamma_t, rho, omega, delta;

    public int[][] mig;
    public int[] migsum;
    public int[][] nzv;    //物品i从主题z中提取的次数，不包括第j个用户-物品对
    public int[] nzvSum;
    public int[][] ngz;    //主题z被分配给g组的次数，不包括第j个用户-物品对
    public int[] ngzSum;
    public int[][] nzu;    //用户u从主题z中抽取的次数，不包括第j个用户-物品对
    public int[] nzuSum;
    public int[][] nuv;    //物品i从用户u中提取的次数，不包括第j个用户-物品对
    public int[] nuvSum;
    public int[][] nuc;    //开关c从用户u中提取的次数，不包括第j个用户-物品对
    public int[][] nzr;
    public int[] nzrSum;
    public int[][] nrv;
    public int[] nrvSum;
    public int[] Cg;
    public int[] Zg;
    public int[] Rg;

    public double[][] lambda;
    public double[][] gz;
    public double[][] uv;
    public double[][] zv;
    public double[][] zu;
    public double[][] zr;
    public double[][] rv;

    public ArrayList<Integer>[] trainset;
    public ArrayList<Integer>[] testset;
    public ArrayList<Integer>[] groups;

    public Model(int u, int v, int g) {
        U = u;
        V = v;
        G = g;
        C = 2;
        Z = 50;
        R = 50;
        alpha = 1;
        beta = 0.01;
        rho = 0.01;
        eta = 0.01;
        gamma = 0.5;
        gamma_t = 0.5;
        eplisilon = 0.2;
        omega = 0.01;
        delta = 0.01;
        mig = new int[V][V];
        migsum = new int[V];
        ngz = new int[G][Z];
        ngzSum = new int[G];
        nzu = new int[Z][U];
        nzuSum = new int[Z];
        nzr = new int[Z][R];
        nzrSum = new int[Z];
        nrv = new int[R][V];
        nrvSum = new int[R];
        nzv = new int[Z][V];
        nzvSum = new int[Z];
        nuv = new int[U][V];
        nuvSum = new int[U];
        nuc = new int[U][C];

        Cg = new int[G];
        Zg = new int[G];
        Rg = new int[G];
        trainset = new ArrayList[G];
        testset = new ArrayList[G];
        groups = new ArrayList[G];
    }

    public double pm(int v) {
        int c = 0;
        for (int j = 0; j < V; j++) {
            if (mig[v][j] > mig[v][c]) {
                c = j;
            }
        }
        return 1.0 * (mig[v][c] + delta) / (migsum[c] + 323 * delta);
    }

    public void generate(int num) {
        initPM();
        train(num);
        gz = estParameter(ngz, ngzSum, alpha);
        zu = estParameter(nzu, nzuSum, beta);
        uv = estParameter(nuv, nuvSum, rho);
        zv = estParameter(nzv, nzvSum, eta);
        zr = estParameter(nzr, nzrSum, eplisilon);
        rv = estParameter(nrv, nrvSum, omega);
        lambda = getLambda();
    }

    public void paramsIteration(int t, int g, int v, int z, int c, int r) {
        ngz[g][z] += t;
        ngzSum[g] += t;
        nzv[z][v] += t;
        nzvSum[z] += t;
        nzr[z][r] += t;
        nzrSum[z] += t;
        nrv[r][v] += t;
        nrvSum[r] += t;
        for (int u : groups[g]) {
            nzu[z][u] += t;
            nzuSum[z] += t;
            nuv[u][v] += t;
            nuvSum[u] += t;
            nuc[u][c] += t;
        }
    }

    public int[] descSort(List<Integer> candlist, double[] a) {
        Integer[] indexes = new Integer[a.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (Integer i1, Integer i2) -> Double.compare(a[i2], a[i1]));
        return asArray(candlist, indexes);
    }

    private int[] asArray(List<Integer> candlist, Integer[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = candlist.get(a[i]);
        }
        return b;
    }

    private void initPM() {
        for (ArrayList<Integer> test : testset) {
            for (int r : test) {
                for (ArrayList<Integer> train : trainset) {
                    for (int c : train) {
                        mig[r][c]++;
                    }
                }
            }
        }
        initPMSum();
    }

    private void initPMSum() {
        for (int c = 0; c < V; c++) {
            for (int r = 0; r < V; r++) {
                migsum[c] += mig[r][c];
            }
        }
    }

    private void train(int num) {
        for (int i = 0; i < num; i++) {
            gibbs();
        }
        System.out.println("model trained");
    }

    private double[][] getLambda() {
        double[][] lambda = new double[U][1];
        for (int i = 0; i < U; i++) {
            lambda[i][0] = 1.0 * (nuc[i][1] + gamma) / (nuc[i][1] + nuc[i][0] + gamma + gamma_t);
        }
        return lambda;
    }

    private void gibbs() {
        double[][] P = new double[Z][R];
        double[] Pcj = new double[C];
        for (int g = 0; g < trainset.length; g++) {
            for (int v : trainset[g]) {
                paramsIteration(-1, g, v, Zg[g], Cg[g], Rg[g]);
                for (int z = 0; z < Z; z++) {
                    double front = (ngz[g][z] + alpha) / (ngzSum[g] + Z * alpha);
                    double count = groups[g].size() * beta;
                    for (int u : groups[g]) {
                        count = count + nzu[z][u];
                    }
                    front = front * (count / (nzuSum[z] + U * beta));
                    if (Cg[g] == 1) {
                        front = front * ((nzv[z][v] + eta) / (nzvSum[z] + V * eta));
                    }
                    for (int r = 0; r < R; r++) {
                        P[z][r] = (nzr[z][r] + eplisilon) / (nzrSum[z] + R * eplisilon)
                                * (nrv[r][v] + omega) / (nrvSum[r] + V * omega) * front;
                    }
                }

                if (Cg[g] == 1) {
                    double numerator = groups[g].size() * gamma;
                    double denominator = groups[g].size() * (gamma + gamma_t);
                    for (int u : groups[g]) {
                        numerator = numerator + nuc[u][1];
                        denominator = denominator + nuc[u][1] + nuc[u][0];
                    }
                    Pcj[Cg[g]] = (numerator / denominator) * ((nzv[Zg[g]][v] + eta) / (nzvSum[Zg[g]] + V * eta));
                }
                if (Cg[g] == 0) {
                    double numerator = groups[g].size() * gamma;
                    double denominator = groups[g].size() * (gamma + gamma_t);
                    double numerator_2 = groups[g].size() * rho;
                    double denominator_2 = groups[g].size() * V * rho;
                    for (int u : groups[g]) {
                        numerator = numerator + nuc[u][0];
                        denominator = denominator + nuc[u][1] + nuc[u][0];
                        numerator_2 = numerator_2 + nuv[u][v];
                        denominator_2 = denominator_2 + nuvSum[u];
                    }
                    Pcj[Cg[g]] = (numerator / denominator) * (numerator_2 / denominator_2);
                }

                double[] ravel = newnorm(ravel(P));// 按行来将二维数组转为一维数组
                int index = draw(ravel);
                int[] zr = unravel_index(index, Z, R);
                if (zr == null) {
                    System.out.println("zr is null!");
                    return;
                }
                Zg[g] = zr[0];
                Rg[g] = zr[1];
                Cg[g] = draw(newnorm(Pcj));
                paramsIteration(1, g, v, Zg[g], Cg[g], Rg[g]);
            }
        }
    }

    private double[][] estParameter(int[][] m, int[] sum, double sp) {
        int r = m.length;
        int c = m[0].length;
        double[][] p = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                p[i][j] = 1.0 * (m[i][j] + sp) / (sum[i] + c * sp);
            }
        }
        return p;
    }

    private double[] ravel(double[][] x) {
        int row = x.length;
        int col = x[0].length;
        double[] z = new double[row * col];
        for (int i = 0; i < row; i++) {
            System.arraycopy(x[i], 0, z, col * i, col);
        }
        return z;
    }

    private int[] unravel_index(int index, int r, int c) {
        if (index > r * c) return null;
        int[] z = new int[2];
        z[0] = index / c;
        z[1] = index - c * z[0];
        return z;
    }

    private int draw(double[] distribution) {
        double p = Math.random();
        for (int i = 0; i < distribution.length; i++) {
            p = p - distribution[i];
            if (p < 0) {
                return i;
            }
        }
        return distribution.length - 1;
    }

    private double[] newnorm(double[] a) {
        double[] b = new double[a.length];
        double sum = 0;
        for (double item : a) {
            sum += item;
        }
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] / sum;
        }
        return b;
    }
}
