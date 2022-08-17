package com.volunteer.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shengqiang
 * @date 2020-08-11 17:31
 */
public class BIOTest {

    public boolean lemonadeChange(int[] bills) {
        if (bills[0] != 5){
            return false;
        }
        int countFive = 1;
        int countTen = 0;
        for (int i = 1;i < bills.length;i ++){
            if (bills[i] == 5){
                countFive += 1;
            } else if (bills[i] == 10){
                if (countFive == 0){
                    return false;
                } else {
                    countFive -= 1;
                    countTen ++;
                }
            } else {
                if (countTen > 0 && countFive > 0){
                    countTen --;
                    countFive --;
                } else if (countFive >= 3){
                    countFive -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        int start = 0;
        int end = 0;
        char startChar = s.charAt(0);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1;i < s.length();i ++){
            if (startChar == s.charAt(i)){
                if (i == s.length() - 1){
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end);
                    lists.add(list);
                }
                end ++;
                continue;
            } else if (end - start >= 2){
                List<Integer> list = new ArrayList<>();
                list.add(start);
                list.add(end);
                lists.add(list);
            }
            start = i;
            end = i;
            startChar = s.charAt(i);
        }
        return lists;
    }



    public static void main(String[] args) {
        System.out.println(new BIOTest().largeGroupPositions("abbxxxxzzy"));
    }
}
