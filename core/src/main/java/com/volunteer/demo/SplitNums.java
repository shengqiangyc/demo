package com.volunteer.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 分割字符串
 * @author shengqiang
 * @date 2020-12-04 10:30
 */
public class SplitNums {

    public boolean isPossible(int[] nums) {
        if (nums.length < 3){
            return false;
        }
        Map<Integer,Integer> countNumsMap = new HashMap<>();
        Map<Integer,Integer> endNumMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (countNumsMap.containsKey(nums[i])){
                Integer count = countNumsMap.get(nums[i]);
                countNumsMap.put(nums[i],count + 1);
            } else {
                countNumsMap.put(nums[i],1);
            }
            endNumMap.put(nums[i],0);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer endNumsPre = endNumMap.getOrDefault(nums[i] - 1,0);
            Integer endNums = endNumMap.getOrDefault(nums[i],0);
            Integer now = countNumsMap.get(nums[i]);
            if (now == 0){
                continue;
            }
            //不存在i - 1结尾的子序列
            if (endNumsPre == 0){
                Integer second = countNumsMap.getOrDefault(nums[i] +1,0);
                Integer third = countNumsMap.getOrDefault(nums[i] +2,0);
                if (second == 0 || third == 0){
                    return false;
                } else {
                    Integer secondEnd = endNumMap.get(nums[i] + 2)                                                                                  == null ? 0 : endNumMap.get(nums[i] + 2);
                    endNumMap.put(nums[i] + 2,secondEnd + 1);
                    countNumsMap.put(nums[i],now - 1);
                    countNumsMap.put(nums[i] +1,second - 1);
                    countNumsMap.put(nums[i] +2,third -1);
                }
            } else {
                endNumMap.put(nums[i],endNums + 1);
                endNumMap.put(nums[i] -1,endNumsPre - 1);
                countNumsMap.put(nums[i],now - 1);
            }
        }
        return true;
    }

    public int mySqrt(int x) {
        if (x == 1){
            return x;
        }
        long result = x;
        while ((result * result)*10 > x){
            result /= 10;
        }
        long next = result;
        long preNUm = 0;
        int index = 0;
        while (result > 0){
            result /= 10;
            index ++;
        }
        long realResult = 0;
        for (int i = index; i > 0; i--) {
            while (next * next < x) {
                preNUm = (int)next;
                next += Math.pow(10, i - 1);
            }
            if (next * next == x){
                return (int)next;
            }
            if (next * next > x){
                if (i == 1){
                    return (int)preNUm;
                }
                realResult += preNUm;
                next = realResult;
            }
        }
        return (int)realResult;
    }

    public static void main(String[] args) {
        System.out.println(new SplitNums().mySqrt(2147480));
    }
}
