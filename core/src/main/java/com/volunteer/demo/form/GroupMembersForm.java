/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.form
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/18下午3:04
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.form;

import com.volunteer.demo.DTO.PageDTO;
import lombok.Data;

/**
 * Description: 获取团队成员入参
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/18下午3:04
 * sinceV1.0
 */
@Data
public class GroupMembersForm extends PageForm{

    private Long groupId;
    private String userName;
    private Integer role;
}
