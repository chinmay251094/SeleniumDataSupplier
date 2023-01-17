package com.digicorp.utils;

import com.digicorp.constants.FrameworkConstants;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public final class ExcelReaderUtils {
    private ExcelReaderUtils() {
    }

    public static List<Map<String, String>> getTestCaseDetails(String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(FrameworkConstants.getDataPath());
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int row = sheet.getLastRowNum();
        int col = sheet.getRow(0).getLastCellNum();

        Map<String, String> map = null;
        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 1; i <= row; i++) {
            map = new HashMap<>();
            for (int j = 0; j < col; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    continue;
                }
                String key = sheet.getRow(0).getCell(j).getStringCellValue();
                String value = null;
                if (sheet.getRow(i).getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    value = NumberToTextConverter.toText(sheet.getRow(i).getCell(j).getNumericCellValue());
                } else {
                    value = String.valueOf(sheet.getRow(i).getCell(j).getStringCellValue());
                }
                map.put(key, value);
            }
            list.add(map);
        }

        if (Objects.nonNull(fis)) {
            fis.close();
        }
        return list;
    }
}
