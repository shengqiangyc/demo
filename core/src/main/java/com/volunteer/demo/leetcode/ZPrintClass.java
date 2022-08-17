/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.leetcode
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2020/1/23:32 PM
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.leetcode;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2020/1/23:32 PM
 * sinceV1.0
 */
public class ZPrintClass {

    public String convert(String s, int numRows) {
        int length = s.length();
        char a[][] = new char[length][numRows];
        int currentLine = 0;
        int currentRow = 0;
        int deadline = (numRows -1) * 2;
        StringBuilder result = new StringBuilder();
        //代表了接下来的变换
        boolean flag = true;
        for (int i = 0;i < s.length(); i++){
            if (i < numRows){
                a[currentLine][i] = s.charAt(i);
                currentRow ++;
                if (currentRow >= numRows){
                    currentRow = numRows - 1;
                }
                continue;
            }
            if (flag){
                currentLine ++;
                currentRow --;
            } else {
                currentRow ++;
            }
            a[currentLine][currentRow] = s.charAt(i);
            if (i % deadline == 0){
                flag = false;
            }
            if (currentRow == numRows -1){
                flag = true;
            }
        }
        for (int m = 0 ; m < numRows ; m ++){
            for (int n = 0 ; n <= currentLine ; n ++){
                if (a[n][m] != '\0' ) {
                    result.append(a[n][m]);
                }
            }
        }
        return result.toString();

    }

    public static void main(String[] args){
        ZPrintClass zPrintClass = new ZPrintClass();
        String s = "AB";
        int row = 1;
        System.out.println(zPrintClass.convert(s,row));

    }
}
