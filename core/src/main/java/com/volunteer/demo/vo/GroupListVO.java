/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/10下午1:40
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 团队列表展示
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/10下午1:40
 * sinceV1.0
 */
@Data
public class GroupListVO {

    private Long groupId;
    private String groupName;
    private String groupImage;
    private String createTime;
    private String groupStatus;
}
