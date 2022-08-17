/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.enums
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/25下午3:38
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.enums;

/**
 * Description: 项目分类
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/25下午3:38
 * sinceV1.0
 */
public enum ActivityTypeEnum {

    CONVENIENT_PEOPLE(1,"便民利民"),
    HELP_POOR(2,"扶贫帮困"),
    SECURITY_STABILITY(3,"治安维稳"),
    HEALTH_CARE(4,"卫生保健"),
    ENVIRONMENTAL_PROTECT(5,"环境保护"),
    PUBLIC_EDUCTION(6,"宣传教育"),
    SPORTS_AND_ENTERTAINMENT(7,"文体娱乐"),
    PSYCHOLOGICAL_COUNSELINGPs(8,"心理咨询");

    private Integer code;
    private String msg;

    ActivityTypeEnum(Integer code, String msg) {
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
        ActivityTypeEnum[] statusArray = values();
        for (ActivityTypeEnum status : statusArray) {
            if (status.code.equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }
}
