package com.nott.poi.excel.factory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 14:39
 * @Modified By:
 **/
public abstract class ExcelFactoryImpl implements ExcelFactory {

    public ArrayList<Object> getCellContentOfRow(Row row, FormulaEvaluator evaluator) {
        ArrayList<Object> cellContent = new ArrayList<>();
        if (row == null) {
            return cellContent;
        }
        int cellNum = row.getLastCellNum();
        for (int i=0; i<cellNum; i++) {
            Cell cell = row.getCell(Short.parseShort(i+""));
            CellValue c = evaluator.evaluate(cell);
            if (c != null) {
                switch (c.getCellType()) {
                    case STRING:
                        String value = c.getStringValue();
                        cellContent.add(value);
                        break;
                    case NUMERIC:
                        double dValue = c.getNumberValue();
                        cellContent.add(dValue);
                        break;
                    default:
                        Object oValue = c.formatAsString();
                        cellContent.add(oValue);
                }
            }
        }
        return cellContent;
    }

    public JSONArray parse(Workbook wb) {
        JSONArray array = new JSONArray();
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        int sheetNum = wb.getNumberOfSheets();
        for (int i=0; i<sheetNum; i++) {
            Sheet sheet = wb.getSheetAt(i);
            int rowNum = sheet.getLastRowNum()+1;
            Row titleRow = sheet.getRow(0);
            ArrayList<Object> titles = getCellContentOfRow(titleRow, evaluator);
            for (int r=1; r<rowNum; r++) {
                Row row = sheet.getRow(r);
                ArrayList<Object> cellContent = getCellContentOfRow(row, evaluator);
                JSONObject json = changeListToJson(titles, cellContent);
                array.add(json);
            }

        }
        return array;
    }

    private JSONObject changeListToJson(ArrayList<Object> titles, ArrayList<Object> cellContent) {
        JSONObject json = new JSONObject();
        for (int i=0; i<titles.size(); i++) {
            String title = (String) titles.get(i);
            Object cellValue = cellContent.get(i);
            json.put(title, cellValue);
        }
        return json;
    }

}
