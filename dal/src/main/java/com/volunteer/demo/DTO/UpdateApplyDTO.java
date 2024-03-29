/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.DTO
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/24下午7:56
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.DTO;

import lombok.Data;

/**
 * Description: 对入队申请的操作
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/24下午7:56
 * sinceV1.0
 */
@Data
public class UpdateApplyDTO {

    private Long userId;
    private Long groupId;
    private Integer status;
}
