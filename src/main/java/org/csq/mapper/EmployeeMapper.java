package org.csq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csq.entity.Employee;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
