/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/12/14下午2:48
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo;

import com.alibaba.dubbo.common.json.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一共有100哥囚犯，排成一排报数，报到奇数就枪毙，偶数就留下，然后继续这个操作，直到最后剩下一个人的时候
 * 问：我们需要站在第几个才能保证最后可以被留下
 * @author shengqiang
 * @date 2019/12/14下午2:48
 * sinceV1.0
 */
public class Learn1 {

    public static void main(String[] args){
        List<Integer> test1 = new ArrayList<>(10);
        for (int i = 0;i <10 ; i++){
            test1.add(i);
        }
        test1.add(98);
    }

}
