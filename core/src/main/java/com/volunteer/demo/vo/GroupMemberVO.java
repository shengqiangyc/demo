/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/18下午3:35
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 团队成员返回参数
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/18下午3:35
 * sinceV1.0
 */
@Data
public class GroupMemberVO {

    private String userLogo;
    private String userName;
    private String realName;
    private String address;
    private String role;
    private String entryDate;
    private String sex;
}
