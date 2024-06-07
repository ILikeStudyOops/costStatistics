package org.csq.controller;

import org.csq.entity.CostRecord;
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

@RestController
@RequestMapping("costRecord")
public class CostRecortController {

    @Autowired
    private CostRecordService costRecordService;

    @PostMapping("/upload")
    public ResponseEntity uploadExcelFile(@RequestParam("file") MultipartFile file) {
        List<CostRecord> costRecords = ExcelReaderUtil.readCostRecordsFromExcel(file);
        costRecordService.saveCostRecords(costRecords);

        return ResponseEntity.ok("上传数据成功");
    }
}
