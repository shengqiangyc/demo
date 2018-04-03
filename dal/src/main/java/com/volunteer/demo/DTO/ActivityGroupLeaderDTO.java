/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.DTO
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/4/2下午2:33
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.DTO;

import lombok.Data;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/4/2下午2:33
 * sinceV1.0
 */
@Data
public class ActivityGroupLeaderDTO extends PageDTO{

    private Long groupId;
    private Long leaderId;
    private Integer status;
}
