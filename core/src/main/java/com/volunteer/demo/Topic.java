package com.volunteer.demo;

import com.alibaba.dubbo.common.json.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shengqiang
 * @date 2022/1/6 8:18 下午
 */
@Data
@AllArgsConstructor
public class Topic {

    private String title;

    private List<Topic> topics;

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        System.out.println(Lists.newArrayList("YH202104285809883900".split(",")));
    }

    public static String append(Topic topic,String topicStr,List<String> strings){
        if (topic.getTopics() == null){
            topicStr += topic.getTitle() + "_";
            strings.add("'" + topicStr + "'");
            return "";
        } else {
            topicStr += topic.getTitle() + "_";
            for (Topic topic1 : topic.getTopics()){
                topicStr += append(topic1, topicStr,strings);
            }
            return "";
        }
    }



}

