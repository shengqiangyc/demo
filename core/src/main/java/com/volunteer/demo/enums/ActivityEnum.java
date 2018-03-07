/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.enums
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午10:28
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.enums;

/**
 * Description: 项目状态枚举
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午10:28
 * sinceV1.0
 */
public enum ActivityEnum {

    ACTIVITY_LIVE(1, "进行中"),
    ACTIVITY_END(2,"已结束");
    private Integer code;
    private String msg;

    ActivityEnum(Integer code, String msg) {
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
        ActivityEnum[] statusArray = values();
        for (ActivityEnum status : statusArray) {
            if (status.code.equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }
}
