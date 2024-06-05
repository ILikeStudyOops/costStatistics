package org.csq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csq.entity.Customer;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}
