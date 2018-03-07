/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.enums
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/4上午10:36
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.enums;

/**
 * Description: 是与不是枚举
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/4上午10:36
 * sinceV1.0
 */
public enum RecordStatusEnum {

    YES(1, "是"),
    NO(0, "不是");

    private Integer code;
    private String msg;

    RecordStatusEnum(Integer code, String msg) {
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
        RecordStatusEnum[] statusArray = values();
        for (RecordStatusEnum status : statusArray) {
            if (status.code.equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }
}
