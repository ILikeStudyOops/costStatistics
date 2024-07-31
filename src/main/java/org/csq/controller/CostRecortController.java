package org.csq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.csq.controller.vo.TableData;
import org.csq.entity.CostRecord;
import org.csq.mapper.CostRecordMapper;
import org.csq.service.CostRecordService;
import org.csq.util.ExcelReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("costRecord")
public class CostRecortController {

    @Autowired
    private CostRecordService costRecordService;
    @Autowired
    private CostRecordMapper costRecordMapper;

    @PostMapping("/upload")
    public ResponseEntity uploadExcelFile(@RequestParam("file") MultipartFile file) {
        List<CostRecord> costRecords = ExcelReaderUtil.readCostRecordsFromExcel(file);
        costRecordService.saveCostRecords(costRecords);

        return ResponseEntity.ok("上传数据成功");
    }

    @PostMapping("/table1")
    public ResponseEntity table1() {
        return ResponseEntity.ok(costRecordMapper.table1());
    }

    @PostMapping("/table2")
    public ResponseEntity table2() {
        List<TableData> jiaotong = costRecordMapper.table2(0);
        List<TableData> zhusu = costRecordMapper.table2(1);
        List<TableData> zhaodai = costRecordMapper.table2(2);
        JSONObject result = new JSONObject();
        result.put("jiaotong", jiaotong.stream().map(j -> j.getValue()).collect(Collectors.toList()));
        result.put("zhusu", zhusu.stream().map(j -> j.getValue()).collect(Collectors.toList()));
        result.put("zhaodai", zhaodai.stream().map(j -> j.getValue()).collect(Collectors.toList()));
        result.put("nameList", jiaotong.stream().map(j -> j.getName()).collect(Collectors.toList()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/table3")
    public ResponseEntity table3() {
        List<TableData> data = costRecordMapper.table3();
        JSONObject result = new JSONObject();
        result.put("types",data.stream().map(d -> d.getType()).collect(Collectors.toList()));
        result.put("values",data.stream().map(d -> d.getValue()).collect(Collectors.toList()));
        return ResponseEntity.ok(result);
    }
}
