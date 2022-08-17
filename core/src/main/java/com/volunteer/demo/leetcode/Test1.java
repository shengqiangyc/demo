package com.volunteer.demo.leetcode;

import java.time.LocalDate;
import java.util.*;

/**
 * @author shengqiang
 * @date 2021/7/22 3:32 下午
 */
public class Test1 {

    public static int maxSubarraySumCircular(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int[] newNums = new int[nums.length * 2];
        for (int i = 0;i < nums.length * 2;i++){
            newNums[i] = nums[i % nums.length];
        }
        return getMax(newNums);

    }

    public static int getMax(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        int length = 1;
        for (int i = 1;i < nums.length;i ++){
            length ++;
            if (dp[i -1] + nums[i] > nums[i] && length <= nums.length/2 -1){
                dp[i] = dp[i -1] + nums[i];
            } else {
                length = 1;
                dp[i] = nums[i];
            }
            if (dp[i] > max && length <= nums.length/2 -1){
                max = dp[i];
            }
        }
        return max;
    }

    public static int getMaxLen(int[] nums) {
        if (nums[0] <= 0 && nums.length == 1){
            return 0;
        }
        int[] positive = new int[nums.length];
        int[] negative = new int[nums.length];
        if (nums[0] > 0){
            positive[0] = 1;
        } else if (nums[0] < 0){
            negative[0] = 1;
        }
        int maxLength = positive[0];
        for (int i = 1; i < nums.length; i ++){
            if (nums[i] > 0){
                positive[i] = positive[i - 1] + 1;
                if (negative[i - 1] > 0){
                    negative[i] = negative[i - 1] + 1;
                }
            } else if (nums[i] < 0){
                negative[i] = positive[i - 1] + 1;
                if (negative[i - 1] > 0){
                    positive[i] = negative[i - 1] + 1;
                }
            }
            if (positive[i] >= maxLength){
                maxLength = positive[i];
            }
        }
        return maxLength;

    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m;i ++){
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int j = 1; j < n;j ++){
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }
        for (int i = 1;i < m;i ++){
            for (int j = 1;j < n;j ++){
                dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }




    public static void main(String[] args) {
        int[][] g = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.printf(Integer.valueOf(minPathSum(g)).toString());
    }
}
