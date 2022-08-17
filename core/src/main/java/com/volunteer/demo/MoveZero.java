package com.volunteer.demo;

import com.google.common.collect.Lists;

/**
 * @author shengqiang
 * @date 2020-11-19 14:52
 */
public class MoveZero {

    public void moveZero(int[] nums){
        int index = 0;
        int zeroStart = -1;
        while (index <= nums.length - 1){
            if (nums[index] == 0 && zeroStart == -1){
                zeroStart = index;
            } else if (nums[index] != 0 && zeroStart != -1){
                nums[zeroStart] = nums[index];
                nums[index] = 0;
                zeroStart ++;
            }
            index ++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1};
        new MoveZero().moveZero(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
