/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.enums
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/18下午3:52
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.enums;

/**
 * Description: 性别枚举
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/18下午3:52
 * sinceV1.0
 */
public enum SexEnum {

    MAN(0, "男"),
    WOMAN(1, "女");

    private Integer code;
    private String msg;

    SexEnum(Integer code, String msg) {
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
        SexEnum[] statusArray = values();
        for (SexEnum status : statusArray) {
            if (status.code.equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }
}
