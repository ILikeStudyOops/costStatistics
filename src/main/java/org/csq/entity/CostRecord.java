package org.csq.entity;

import lombok.Data;

import java.io.Serializable;

/**
* 
* @TableName cost_record
*/
@Data
public class CostRecord implements Serializable {

    /**
    * id
    */
    private String id;
    /**
    * 外键-员工id
    */
    private String employeeId;
    /**
    * 年份
    */
    private Integer year;
    /**
    * 月份
    */
    private Integer month;
    /**
    * 外键-客户id
    */
    private String fkCustomerId;
    /**
    * 费用类型：0：交通费；1：住宿费；3：招待费
    */
    private Integer type;
    /**
    * 金额
    */
    private Double money;

}
