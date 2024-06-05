package org.csq.controller;

import org.csq.entity.Customer;
import org.csq.entity.Result;
import org.csq.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer")
public class UserController {

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("getCustomerList")
    public Result getCustomerList(){
        List<Customer> customers = customerMapper.selectList(null);
        return new Result().back(200,"查询成功",true,customers);
    }
}
