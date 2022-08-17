package com.volunteer.demo;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author shengqiang
 * @date 2020-11-17 11:05
 */
public class SumThreeTest {

    public Map<String,Integer> map = new HashMap<>();

    public static List<List<Integer>> sumThree(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int k = 0; k < nums.length - 2 ; k ++ ){
            if (nums[k] > 0){
                break;
            }
            if (k > 0 && nums[k] == nums[k-1]){
                continue;
            }
            int i = k + 1,j = nums.length - 1;
            while (i < j){
                int sum = nums[k] + nums[i] + nums[j];
                if (sum == 0){
                    List<Integer> re = new ArrayList<>();
                    re.add(nums[k]);
                    re.add(nums[i]);
                    re.add(nums[j]);
                    result.add(re);
                    while (i < j && nums[i] == nums[++i]);
                    while (i < j && nums[j] == nums[--j]);
                } else if (sum < 0){
                    while (i < j && nums[i] == nums[++i]);
                } else {
                    while (i < j && nums[j] == nums[--j]);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> sumFour(int[] nums,int target){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int m = 0;m < nums.length - 3; m ++){
            if (nums[m] + nums[m+1] + nums[m+2] + nums[m+3] > target){
                break;
            }
            if (nums[m] + nums[nums.length - 3]+ nums[nums.length - 2]+ nums[nums.length - 1] < target){
                continue;
            }
            for (int k = m + 1;k < nums.length - 2; k++){
                if (k > m + 1 && nums[k] == nums[k-1]){
                    continue;
                }
                if (nums[m] + nums[k] + nums[k + 1] + nums[k+2] > target) {
                    break;
                }
                if (nums[m] + nums[k] + nums[nums.length - 1] + nums[nums.length - 2] < target){
                    continue;
                }
                int i = k + 1,j = nums.length - 1;
                while (i < j){
                    int sum = nums[m] + nums[k] + nums[i] + nums[j];
                    if (sum == target){
                        List<Integer> re = new ArrayList<>(Arrays.asList(nums[m],nums[k],nums[i],nums[j]));
                        result.add(re);
                        while (i < j && nums[i] == nums[++i]);
                        while (i < j && nums[j] == nums[--j]);
                    } else if (sum < target){
                        while (i < j && nums[i] == nums[++i]);
                    } else {
                        while (i < j && nums[j] == nums[--j]);
                    }
                }
            }
        }
        return result;
    }

    public static int sumThreeClosest(int[] nums,int target){
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int k = 0;k < nums.length - 2; k++){
            int i = k + 1,j = nums.length - 1;
            while (i < j){
                int sum = nums[k] + nums[i] + nums[j];
                int dif = sum - target;
                if (Math.abs(dif) < Math.abs(result - target)){
                    result = sum;
                }
                if (dif == 0){
                    return result;
                } else if (dif < 0){
                    i ++;
                } else{
                    j --;
                }
            }
        }
        return result;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        int result = 0;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target){
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

/*    public int findMagicIndex(int[] nums) {

    }

    public int findMagicIndex(int[] nums,int start,int end) {

    }*/

    public int missingNumber(int[] nums) {
        int left = 0,right = nums.length - 1;
        int result = 0;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (mid >= nums[mid]){
                left = mid + 1;
            } else {
                right = mid -1;
            }
            result = left;
        }
        return left;
    }


    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1){
            return 1;
        }
        if (map.containsKey(m + "," + n)){
            return map.get(m + "," + n);
        } else {
            int result = uniquePaths(m - 1,n) + uniquePaths(m, n-1);
            map.put(m + "," + n,result);
            return result;
        }
    }

    public void reorderList(NodeInsert.ListNode head) {

    }


    public static void main(String[] args) {
        int[] nums = {0,1,3};
        System.out.println(new SumThreeTest().missingNumber(nums));
    }
}
