/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.enums
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/4上午11:07
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.enums;

/**
 * Description: 团队角色枚举
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/4上午11:07
 * sinceV1.0
 */
public enum GroupRoleEnum {

    NORMAL_MEMBER(1, "普通成员"),
    ADMIN(2, "管理员"),
    CREATOR(3,"创建者");

    private Integer code;
    private String msg;

    GroupRoleEnum(Integer code, String msg) {
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
        GroupRoleEnum[] statusArray = values();
        for (GroupRoleEnum status : statusArray) {
            if (status.code.equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }
}
