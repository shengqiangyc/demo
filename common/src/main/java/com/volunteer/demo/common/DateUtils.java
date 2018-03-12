/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.common
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/11上午11:43
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 时间转换类
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/11上午11:43
 * sinceV1.0
 */
public class DateUtils {

    public static String convertDateToYMDHMS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }

}
