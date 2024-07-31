package org.csq.entity;

import lombok.Data;

import java.io.Serializable;


/**
* 
* @TableName employee
*/
@Data
public class Employee implements Serializable {

    /**
    * 员工id
    */
    private String id;
    /**
    * 员工姓名
    */
    private String name;

    private Integer sort;
}
