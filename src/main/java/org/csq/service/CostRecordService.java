package org.csq.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.csq.entity.CostRecord;
import org.csq.entity.Customer;
import org.csq.entity.Employee;
import org.csq.mapper.CostRecordMapper;
import org.csq.mapper.CustomerMapper;
import org.csq.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CostRecordService {

    @Autowired
    private CostRecordMapper costRecordMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CustomerMapper customerMapper;

    public void saveCostRecords(List<CostRecord> costRecords) {
        costRecords.stream().forEach(costRecord -> {
            //根据客户名称和客户名称和员工姓名获取对应的id
            String employeeId = employeeMapper.selectOne(new LambdaQueryWrapper<Employee>().eq(Employee::getName, costRecord.getFkEmployeeId())).getId();
            if (!ObjectUtils.isEmpty(employeeId)) {
                costRecord.setFkEmployeeId(employeeId);
            }
            String customerId = customerMapper.selectOne(new LambdaQueryWrapper<Customer>().eq(Customer::getName, costRecord.getFkCustomerId())).getId();
            if (!ObjectUtils.isEmpty(customerId)) {
                costRecord.setFkCustomerId(customerId);
            }
            costRecordMapper.insert(costRecord);
        });
    }
}
