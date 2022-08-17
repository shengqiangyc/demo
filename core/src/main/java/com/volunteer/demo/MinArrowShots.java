package com.volunteer.demo;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * @author shengqiang
 * @date 2020-11-23 09:54
 */
public class MinArrowShots {

    public int count;

    public int longestSubstring(String s, int k) {

        getCount(s,k);
        return count;
    }

    public void getCount(String s,int k){
        Map<Character,Integer> result = new HashMap<>();
        for (int i = 0;i <s.length() ; i++){
            if (result.containsKey(s.charAt(i))){
                Integer count = result.get(s.charAt(i));
                result.put(s.charAt(i),count +1);
            } else {
                result.put(s.charAt(i),1);
            }
        }
        if (s.length() == 0){
            return;
        }
        char split = 'a';
        boolean hasSplit = false;
        for (int i = 0;i <s.length() ; i++){
            Integer countChar = result.get(s.charAt(i));
            if (countChar < k) {
                hasSplit = true;
                split = s.charAt(i);
            }
        }
        if (!hasSplit){
            count = Math.max(s.length(),count);
            return;
        }
        String[] strs = s.split(String.valueOf(split));
        if (strs.length == 0){
            return;
        }
        for (int j = 0; j< strs.length ; j++){
            getCount(strs[j],k);
        }
        return;
    }


    public int findString(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;
        while (left < right){
            while ("".equals(words[left])){
                left ++;
            }
            while ("".equals(words[right])){
                right --;
            }
            if (words[left].compareTo(s) == 0){
                return left;
            }
            if (words[right].compareTo(s) == 0){
                return right;
            }
            if (right - left <= 1){
                return -1;
            }
            if (words[left].compareTo(s) > 0 || words[right].compareTo(s) < 0){
                return -1;
            }
            int mid = (left + right) /2;
            while ("".equals(words[mid]) && mid >= left){
                mid --;
            }
            if (words[mid].compareTo(s) == 0){
                return mid;
            }
            if (words[mid].compareTo(s) > 0){
                right = mid;
            } else {
                left = mid;
            }
        }
        return -1;
    }






    public static void main(String[] args) {
        //String a = "ababacb";
        String[] aaa = {"at","ball","car","dad"};
        System.out.println(new MinArrowShots().findString(aaa,"balll"));
        //System.out.println(new MinArrowShots().longestSubstring(a,3));
    }
}
