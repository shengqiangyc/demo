/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.enums
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午8:42
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.enums;

import com.volunteer.demo.manager.GroupManager;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午8:42
 * sinceV1.0
 */
public enum GroupStatusEnum {

    CAN_ENTRY(1, "可加入"),
    CANNOT_ENTRY(2, "不可加入");

    private Integer code;
    private String msg;

    GroupStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsgByCode(Integer code) {
        GroupStatusEnum[] statusArray = values();
        for (GroupStatusEnum status : statusArray) {
            if (status.code.equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }
}
