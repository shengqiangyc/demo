/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/4/2下午4:01
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/4/2下午4:01
 * sinceV1.0
 */
@Data
public class GroupActivityVO {

    private Long activityId;
    private String activityName;
    private String city;
    private String type;
    private String createTime;
    private String leaderName;
    private String status;
    private Integer statusInt;
    private String url;
    private Long leaderId;


}
