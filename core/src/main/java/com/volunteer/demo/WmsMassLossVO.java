package com.volunteer.demo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by keben on 2017/4/25.
 */
@Data
public class WmsMassLossVO implements Serializable{

    //异常车辆总计
    private Integer totalCount = 0;

    //入库异常
    private Integer entryLossCount = 0;

    //在库异常
    private Integer deliveryLossCount = 0;

    //入&在库异常
    private Integer entryAndDeliveryCount = 0;
}
