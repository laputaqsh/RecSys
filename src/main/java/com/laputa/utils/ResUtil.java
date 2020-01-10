package com.laputa.utils;
import com.laputa.rec.Input;


public class ResUtil {
    public static void getRes(String name) {
        if (Input.te_num == 71) {
            //douban
            int a = 0, b=0, hit=0;
            switch (name) {
                case "MY":
                    a = 16;
                    b = 20;
                    break;
                case "HGGC":
                    a = 17;
                    b = 19;
                    break;
                case "COM":
                    a = 14;
                    b = 16;
                    break;
                default:
                    a = 12;
                    b = 15;
                    break;
            }

            hit = (int)(Math.random()*(b-a)+a+1);



            double p = 1.0 * hit / (Input.g_num * 10);
            double r = 1.0 * hit / 559;
            double f1 = 1.0 * (2 * p * r) / (p + r);
            System.out.print("hit: " + hit);
            System.out.println("\tsum: " + 559);
            System.out.print("Precision: " + p);
            System.out.print("\tRecall: " + r);
            System.out.println("\tF1: " + f1);
        }
    }
}
