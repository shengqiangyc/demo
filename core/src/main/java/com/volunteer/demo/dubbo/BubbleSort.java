/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.dubbo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/21下午8:11
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.dubbo;

/**
 * 冒泡排序
 * @author shengqiang
 * @date 2019/9/21下午8:11
 * sinceV1.0
 */
public class BubbleSort {

    public static void main(String[] args){
        int a[] = {1,3,6,7,8,10,4,20,11,50,38,43,44,9,5};
        for (int i = 0;i < a.length - 1;i++){
            for (int j = i ;j < a.length - 1 ;j ++){
                if (a[j] > a[j + 1]){
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        String b = "abcde";
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);

        }
    }
}
