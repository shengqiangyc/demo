/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.dubbo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/21下午8:35
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.dubbo;

/**
 * 快速排序
 * @author shengqiang
 * @date 2019/9/21下午8:35
 * sinceV1.0
 */
public class QuickSort {


    public void qSort(int a[],int begin,int end) {
        if (begin < end) {
            int base = a[begin];
            int low = begin;
            int high = end;
            while (low != high) {
                while (low < high && a[high] > base) {
                    high--;
                }
                while (low < high && a[low] < base) {
                    low++;
                }
                if (low < high) {
                    int temp = a[low];
                    a[low] = a[high];
                    a[high] = temp;
                }
            }
            a[low] = base;
            qSort(a, begin, low - 1);
            qSort(a, low + 1, end);
            }
    }

    public static void main(String[] args){
        int a[] = {12,1,3,6,7,8,10,4,20,11,50,38,43,44,9,5};
        new QuickSort().qSort(a,0,a.length-1);
        for (int i = 0; i < a.length - 1; i++) {
            System.out.println(a[i]);
        }
    }
}
