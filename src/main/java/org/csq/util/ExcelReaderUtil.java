package org.csq.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.csq.entity.CostRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ExcelReaderUtil {
    public static List<CostRecord> readCostRecordsFromExcel(MultipartFile file) {
        List<CostRecord> costRecords = new ArrayList<>();

        try {
            InputStream fis = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);

            // Skip header row
            Iterator<Row> iterator = sheet.iterator();
            if (iterator.hasNext()) {
                iterator.next();
            }

            while (iterator.hasNext()) {
                Row row = iterator.next();
                CostRecord record = new CostRecord();

                record.setYear((int) Math.round(row.getCell(0).getNumericCellValue()));
                record.setMonth((int) Math.round(row.getCell(1).getNumericCellValue()));
                record.setFkCustomerId(row.getCell(2).getStringCellValue());
                record.setFkEmployeeId(row.getCell(6).getStringCellValue());
                for (int i = 0; i < 3; i++) {
                    Double cellValue = row.getCell(3 + i).getNumericCellValue();
                    if (!ObjectUtils.isEmpty(cellValue)){
                        CostRecord costRecord = new CostRecord();
                        BeanUtils.copyProperties(record,costRecord);
                        costRecord.setId(UUID.randomUUID().toString());
                        costRecord.setMoney(cellValue);
                        costRecord.setType(i);
                        costRecords.add(costRecord);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return costRecords;
    }
}
