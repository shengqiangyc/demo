/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/25下午3:28
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 活动详情展示
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/25下午3:28
 * sinceV1.0
 */
@Data
public class ActivityDetailVO {

    private Long activityId;
    private String activityName;
    private String status;
    private String activityType;
    private String leaderName;
    private String city;
    private String description;
    private String image;
    private String createTime;
}
