package org.csq.entity;

import lombok.Data;

import java.io.Serializable;

/**
* 
* @TableName customer
*/
@Data
public class Customer implements Serializable {

    /**
    * 客户id
    */
    private String id;
    /**
    * 客户名称
    */
    private String name;

}
