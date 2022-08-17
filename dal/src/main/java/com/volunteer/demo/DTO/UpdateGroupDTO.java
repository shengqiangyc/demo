/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.DTO
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/4/4上午11:02
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.DTO;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

/**
 * Description: 修改团队信息相关参数
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/4/4上午11:02
 * sinceV1.0
 */
@Data
public class UpdateGroupDTO {

    private Long groupId;
    private String description;
    private String require;
    private Integer status;
}
