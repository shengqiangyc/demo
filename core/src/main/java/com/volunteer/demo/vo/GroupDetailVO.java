/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/4上午10:07
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 团队详情
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/4上午10:07
 * sinceV1.0
 */
@Data
public class GroupDetailVO {

    private String groupName;
    private Long groupId;
    private Integer groupCount;
    private String status;
    private Integer statusInt;
    private String city;
    private String groupLeader;
    private String groupQq;
    //用户是否是团队成员
    private Integer userRole;
    private String introduction;
    private String require;
    private String image;
    private Integer applyRecord;
    //用户的团队是否达到三个
    private Integer countUserGroup;
    private String createTime;

}
