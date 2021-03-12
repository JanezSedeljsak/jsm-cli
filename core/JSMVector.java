package core;

import java.util.*;
import java.math.*;

public class JSMVector {

    public static <T extends Number, U extends Collection<T>> double vectorSum(U nums) {
        double val = 0;
        for (Number num: nums) {
            val += (double)num;
        }
        return val;
    }  

    public static <T extends Number, U extends Collection<T>> double vectorProduct(U nums) {
        double val = 1;
        for (Number num: nums) {
            val *= (double)num;
        }
        return val;
    } 

    public static <T extends Number, U extends Collection<T>> double vectorAvg(U nums) {
        return arithmeticAvg(nums);
    } 

    public static <T extends Number, U extends Collection<T>> double arithmeticAvg(U nums) {
        int len = nums.size();
        double sum = vectorSum(nums);
        return sum/(double)len;
    } 

    public static <T extends Number, U extends Collection<T>> double geometricAvg(U nums) {
        double val = vectorProduct(nums);
        return Math.pow(val, 1/nums.size());
    } 

    public static <T extends Number, U extends Collection<T>> double harmonicAvg(U nums) {
        int n = nums.size();
        int val = 0;
        for (Number num: nums) {
            val += 1/(double)num;
        }
        return n/val;
    } 

    public static <T extends Number, U extends Collection<T>> double harmonicAvg(U nums, Double... power) {
        double k = 2;
        int n = nums.size();
        int val = 0;
        for (Number num: nums) {
            val += Math.pow((double)num, k);
        }
        return Math.pow(val/n, 1/n);
    } 

    /*public static <T extends Number, U extends Collection<T>> double meadian(U nums) {
        int n = nums.size();
        ((ArrayList<Double>) nums).sort((num1, num2) -> (int)(num1 - num2));
        return (double)nums.toArray()[n/2];
    } 

    public static <T extends Number, U extends Collection<T>> double mean(U nums) {
        HashMap<T, Integer> occurences = new HashMap<T, Integer>();
        for (Number num: nums) {
            if (occurences.containsKey(num)) {
                occurences.put(num, occurences.get(num)+1);
            } else {
                occurences.put(num, 1);
            }
        }

        int tmpMax = -1;
        double tmpNum;
        for (Number key: occurences.keySet()) {
            int current = occurences.get(key);
            if (current > tmpMax) {
                tmpMax = current;
                tmpNum = key;
            }
        }

        return tmpNum;
    } 



    public static void main(String[] args) {
        ArrayList<Double> nums = new ArrayList<Double>(){{
            add(0.321);
            add(321.33);
            add(1.3);
        }};
        double x1 = vectorSum(nums);
        double[] medianSearch = new double[] {1,3,3,4,5,5,6,8};
        double x2 = .4;
        System.out.printf("x1: %f, x2: %f", x1, x2);
    }*/
}
