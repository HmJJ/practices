package com.nott.external.code.capture.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nott.external.code.capture.vo.CaptureVo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/12/9 16:36
 * @Modified By:
 **/
@Service
public class CaptureService {

    private static Logger log = LoggerFactory.getLogger(CaptureService.class);
    private static List<List<String>> heads = new ArrayList<>();
    private static String URL;
    private static String KEY;
    private static String FILENAME;

    // 查询地区名称
    /*@Autowired
    private PlaceQueryService placeQueryService;*/

    public String getInfoFromExcel(CaptureVo captureVo) {
        String filePath = captureVo.getFilePath();
        if (StringUtils.isEmpty(filePath)) {
            return "请输入文件路径";
        }
        log.info("filePath: {}", filePath);
        String suffix = filePath.substring(filePath.lastIndexOf(".")+1);
        List<String> urls = new ArrayList<>();
        try(InputStream is = new FileInputStream(filePath)) {
            ExcelReader excelReader = new ExcelReader(is, suffix.contains("xlsx") ? ExcelTypeEnum.XLSX : ExcelTypeEnum.XLS, null, new AnalysisEventListener() {
                @Override
                public void invoke(Object object, AnalysisContext analysisContext) {
                    log.info("解析到一条数据：{}", object);
                    if (object != null) {
                        ArrayList<String> data = (ArrayList<String>) object;
                        if (data.size() > 1) {
                            urls.add(data.get(1));
                        }
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    log.info("解析完成！");
                }
            });
            excelReader.read();
        }catch (Exception e) {
            e.printStackTrace();
        }

        for (String url : urls) {
            CaptureVo vo = new CaptureVo();
            vo.setUrl(url);
            getInfoXlsx(vo);
        }

        return "ALL done!";
    }

    public String getInfoXlsx(CaptureVo captureVo) {
        if (StringUtils.isEmpty(captureVo.getUrl())) {
            return "请输入url";
        }
        FILENAME = "";
        KEY = "";
        heads.clear();
        URL = captureVo.getUrl();
        KEY = StringUtils.isEmpty(captureVo.getKey()) ? "stores" : captureVo.getKey();
        int shopNumber = 0;
        List<List<Object>> rows = new ArrayList<>();

        int currentPage = 1;
        JSONObject object = getByPage(0, currentPage);
        int prePageTotal = (Integer) object.get("total");
        int pageTotal = (int) Math.ceil(prePageTotal/10.0);
        for (int j = 1; j <= pageTotal; j++) {
            JSONObject jsonObject = getByPage(0, j);
            if (jsonObject.get("stores") == null) {
                continue;
            }
            JSONArray result = jsonObject.getJSONArray(KEY);
            for (int s = 0; s < result.size(); s++) {
                shopNumber++;

                List<Object> row = new ArrayList<>();
                JSONObject shop = result.getJSONObject(s);
                Set<String> keySet = shop.keySet();
                Iterator<String> keyIterator = keySet.iterator();
                while (keyIterator.hasNext()) {
                    List<String> head = new ArrayList<>();
                    String key = keyIterator.next();
                    String value = StringEscapeUtils.unescapeHtml4(shop.getString(key));
                    row.add(value);
                    /*if ("district".equals(key)) {
                        List<String> area = new ArrayList<>();
                        String placeName = getArea(value);
                        row.add(0, placeName);
                        area.add("region");
                        if (shopNumber == 1) {
                            heads.add(0, area);
                        }
                    }*/
                    if (shopNumber == 1) {
                        head.add(key);
                        heads.add(head);
                    }
                }
                log.info("row: {}", row.toString());
                row.add(shop.toJSONString());
                rows.add(row);
            }
        }

        createXlsx(rows);

        log.info("Capture-getInfo: All Done!");

        return "All Done!";

    }

    //https://map.taobao.com/item/api/itemStoreList.do?locType=current&_input_charset=utf-8&source=map_itemdetail&pageSize=20&pageNo=1&areaCode=310115&itemId=604699475944&tbToken=eee17f6759e3a
    public JSONObject getByPage(int firstAddCode, int currentPage) {
        String url = getUrl(URL, currentPage);
        JSONObject result = get(url);
        return result;
    }

    public JSONObject get(String url) {
        String result = "";
        BufferedReader reader = null;

        try {
            java.net.URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0(compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                int a = 0;
                a++;
                if (a > 5) {
                    System.out.println(map.get(key));
                }
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            System.out.println("中断");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        JSONObject value = JSONObject.parseObject(result);

        return value;

    }

    public void createXlsx(List<List<Object>> rows) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        Random random = new Random();
        String filePath = "D:\\data\\shopbox\\" + FILENAME+dtf.format(now)+ random.nextInt(10000) +".xlsx";
        OutputStream out = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(filePath);
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("店铺信息");
            Table table = new Table(1);
            table.setHead(heads);
            writer.write1(rows, sheet, table);
            writer.finish();

        } catch (Exception e) {
            log.info(e.getCause().getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.info(e.getCause().getMessage());
            }
        }

    }

    /*public String getArea(String code) {
        Place country = null;
        Place province = null;
        Place city = null;
        Place district = null;
        Boolean flag = true;
        String region = "";
        try {
            district = placeQueryService.findByCode(code);
        } catch (Exception e) {
            log.info(e.getMessage());
            flag = false;
        }
        if (flag) {
            try {
                city = placeQueryService.findByCode(district.getParentCode());
            } catch (Exception e) {
                log.info(e.getMessage());
                flag = false;
                region = district.getName();
            }
        }
        if (flag) {
            try {
                province = placeQueryService.findByCode(city.getParentCode());
            } catch (Exception e) {
                log.info(e.getMessage());
                flag = false;
                region = city.getName()+district.getName();
            }
        }
        if (flag) {
            try {
                country = placeQueryService.findByCode(province.getParentCode());
            } catch (Exception e) {
                log.info(e.getMessage());
                flag = false;
                region = province.getName()+city.getName()+district.getName();
            }
        }
        if (flag) {
            region = country.getName()+province.getName()+city.getName()+district.getName();
        }
        if (!FILENAME.contains("_")) {
            FILENAME = region + "_" + FILENAME;
        }
        return region;
    }*/

    public String getUrl(String url, int currentPage) {
        String[] splits = url.split("&");
        String newUrl = "";
        Boolean flag = true;
        for (String str : splits) {
            if (str.startsWith("jsoncallback") || str.startsWith("callback")) {
                continue;
            }
            if (str.contains("?") && "".equals(FILENAME)) {
                FILENAME = str.substring(str.lastIndexOf("itemid=")+1);
            }
            if (str.startsWith("pageNo")) {
                flag = false;
                str = str.substring(0, 7);
                str = str + currentPage;
                System.out.println(str);
            }
            if (!"".equals(newUrl)) {
                newUrl += "&";
            }
            newUrl += str;
        }
        if (flag) {
            newUrl = newUrl + "&pageNo=" + currentPage;
        }
        log.info("url: {}", newUrl);
        return newUrl;
    }

}
