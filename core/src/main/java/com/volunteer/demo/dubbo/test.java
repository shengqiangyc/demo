/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.dubbo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/10/24下午7:54
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.dubbo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/10/24下午7:54
 * sinceV1.0
 */
public class test {


    private void test1() throws Exception{
        List<Integer> list = Lists.newArrayList(1,2,3);
        System.out.println(list.get(4));
    }

    private void test2() throws Exception{
        test1();
    }

    public static void main(String[] args){
        try {
            new test().test2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行代码块");
        }
    }
}
