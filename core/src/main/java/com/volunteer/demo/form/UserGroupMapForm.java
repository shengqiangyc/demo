/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.form
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/4上午11:25
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.form;

import lombok.Data;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/4上午11:25
 * sinceV1.0
 */
@Data
public class UserGroupMapForm {

    private Long groupId;
    private String groupName;
    private Long userId;
    private Integer userRole;
    //操作类型
    private Integer performType;
}
