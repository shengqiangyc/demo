/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/24下午9:20
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 我的入队申请
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/24下午9:20
 * sinceV1.0
 */
@Data
public class MyApplyListVO {

    private Long applyId;
    private String groupName;
    private String applyDate;
    private String applyStatus;
}
