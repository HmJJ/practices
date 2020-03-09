package com.nott.poi.excel.factory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 14:39
 * @Modified By:
 **/
public abstract class ExcelFactoryImpl<T> implements ExcelFactory {

    public static final Logger log = LoggerFactory.getLogger(ExcelFactoryImpl.class);

    /************************************************************************************************/
    /* 读Excel */

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
                    case BOOLEAN:
                        String bValue = String.valueOf(c.getBooleanValue());
                        cellContent.add(bValue);
                        break;
                    case STRING:
                        String value = c.getStringValue();
                        cellContent.add(value);
                        break;
                    case NUMERIC:
                        Object dValue = null;
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                            Date date = HSSFDateUtil.getJavaDate(c.getNumberValue());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            dValue = sdf.format(date);
                        } else {//处理数值格式
                            dValue = c.getNumberValue();
                        }
                        cellContent.add(dValue);
                        break;
                    default:
                        Object oValue = c.formatAsString();
                        cellContent.add(oValue);
                }
            } else {
                cellContent.add("");
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

    /*************************************************************************************************/
    /* 写Excel */

    public byte[] writeData(Workbook wb, List<T> params) throws IOException {
        assert params != null && !params.isEmpty();
        T obj = params.get(0);
        List<String> titles = getTitles(obj);
        Sheet sheet = buildDataSheet(wb, titles);
        int rowNum = 1;
        Iterator<T> it = params.iterator();
        while (it.hasNext()) {
            T param = it.next();
            if (param == null) {
                continue;
            }
            Row row = sheet.createRow(rowNum++);
            convertDataToRow(param, row);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        byte[] bytes = os.toByteArray();
        return bytes;
    }

    public List<String> getTitles(T clazz) {
        List<String> titles = new ArrayList<>();
        Field[] fields = clazz.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            titles.add(field.getName());
        }
        return titles;
    }

    private Sheet buildDataSheet(Workbook wb, List<String> titles) {
        Sheet sheet = wb.createSheet();
        for (int i = 0; i < titles.size(); i++) {
            sheet.setColumnWidth(i, 4000);
        }
        sheet.setDefaultRowHeight((short) 400);
        CellStyle cellStyle = buildHeadCellStyle(wb);
        Row head = sheet.createRow(0);
        for (int i = 0; i < titles.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }

    private CellStyle buildHeadCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private void convertDataToRow(T data, Row row) {
        int cellNum = 0;
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            String type = field.getGenericType().toString();
            Field resultField = Arrays.stream(fields).filter(fd -> fd.getName().equals(name)).findFirst().get();
            Object obj = null;
            resultField.setAccessible(true);
            try {
                obj = resultField.get(data);
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
            Cell cell;
            cell = row.createCell(cellNum++);
            switch (type) {
                case "class java.lang.Long":
                    if (obj == null) {
                        obj = 0L;
                    }
                    cell.setCellValue((Long) obj);
                    break;
                case "class java.lang.String":
                    if (obj == null) {
                        obj = "";
                    }
                    cell.setCellValue((String) obj);
                    break;
                case "class java.lang.Integer":
                    if (obj == null) {
                        obj = 0;
                    }
                    cell.setCellValue((Integer) obj);
                    break;
                case "class java.util.Date":
                    if (obj == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue((Date) obj);
                    }
                    break;
                case "class java.util.Calendar":
                    if (obj == null) {
                        obj = 0;
                    }
                    cell.setCellValue((Calendar) obj);
                    break;
            }
        }
    }

}
