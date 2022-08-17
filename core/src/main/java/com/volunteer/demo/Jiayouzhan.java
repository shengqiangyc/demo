package com.volunteer.demo;

/** 加油站算法
 * @author shengqiang
 * @date 2020-11-18 11:26
 */
public class Jiayouzhan {

    public int getResult(int[] gaps,int[] cost){
        int length = gaps.length;
        for (int i = 0 ;i <length; i++){
            int remain = 0;
            int j = i;
            while (remain >= 0){
                remain = remain + gaps[j % length] - cost[j % length];
                if (j != i && (j % length) == i){
                    return i;
                }
                if (remain >= 0) {
                    j++;
                }
            }
        }
        return -1;

    }

    public int numberOfSteps (int num) {
        int index = 0;
        if (num == 0){
            return index;
        }
        while (num > 0){
            index ++;
            if (num % 2 == 0){
                num /= 2;
            } else {
                num -= 1;
            }
            if (num == 0){
                return index;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int[][] matrix = {{1,2,3,4},
                {5,1,2,3},
                {9,5,1,2}};
        System.out.println(matrix.length);
        //System.out.println(new Jiayouzhan().getResult(gas,cost));
    }

}
