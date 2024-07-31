package org.csq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.csq.controller.vo.TableData;
import org.csq.entity.CostRecord;

import java.util.List;

@Mapper
public interface CostRecordMapper extends BaseMapper<CostRecord> {
    @Select("select b.name,sum(a.money) as value " +
            "from cost_record a join customer b on a.fk_customer_id = b.id " +
            "where year = YEAR(NOW()) and month = MONTH(NOW()) " +
            "GROUP BY b.name")
    List<TableData> table1();

    @Select("select name,sum(money) as value " +
            "from ( " +
            "select b.name,a.money  " +
            "from cost_record a join employee b on a.fk_employee_id = b.id  " +
            "where year = YEAR(NOW()) and month = MONTH(NOW()) and a.type = #{type} " +
            "ORDER BY b.sort " +
            ") x " +
            "group by b.name")
    List<TableData> table2(Integer type);

    @Select("select " +
            "CASE " +
            "WHEN type = 0 THEN '交通费' " +
            "WHEN type = 1 THEN '住宿费' " +
            "WHEN type = 2 THEN '招待费' " +
            "ELSE '其他费用' " +
            "END AS type," +
            "sum(money) as value from cost_record " +
            "where year = YEAR(NOW()) and month = MONTH(NOW()) " +
            "GROUP BY type ")
    List<TableData> table3();
}
