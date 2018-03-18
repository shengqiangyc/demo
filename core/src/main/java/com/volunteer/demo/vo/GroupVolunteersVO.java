/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/18下午4:36
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 团队成员管理页面返回参数
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/18下午4:36
 * sinceV1.0
 */
@Data
public class GroupVolunteersVO {

    /**
     * 团队id
     */
    private Long groupId;

    /**
     * 当前用户角色
     */
    private Integer role;

    /**
     * 团队成员数量
     */
    private Integer count;
}
