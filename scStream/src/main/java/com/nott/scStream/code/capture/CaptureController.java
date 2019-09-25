package com.nott.scStream.code.capture;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nott.scStream.code.capture.vo.CaptureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/30 16:43
 * @Modified By:
 **/
@RestController
@RequestMapping(value = "capture")
public class CaptureController {

    private static Logger log = LoggerFactory.getLogger(CaptureController.class);

    @PostMapping(value = "getInfo")
    public String getInfo(@RequestBody CaptureVo vo) {

        int shopNumber = 0;
        List<List<Object>> rows = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            int currentPage = 1;
            JSONObject object = get(i, currentPage);
            int prePageTotal = (Integer) object.get("storeCount");
            int pageTotal = (int) Math.ceil(prePageTotal/10.0);
            for (int j = 1; j <= pageTotal; j++) {
                JSONObject jsonObject = get(i, j);
                if (jsonObject.get("locShopProductSet") == null) {
                    continue;
                }
                JSONArray result = jsonObject.getJSONArray("locShopProductSet");
                for (int s = 0; s < result.size(); s++) {
                    shopNumber++;

                    JSONObject shop = result.getJSONObject(s);
                    String firstAddName = shop.getString("firstAddName");
                    String secondAddName = shop.getString("secondAddName");
                    String thirdAddName = shop.getString("thirdAddName");
                    String shopName = shop.getString("locShopName");
                    String shopAddress = shop.getString("locShopFullAddr");
                    JSONObject extend = shop.getJSONObject("extend");
                    String score = "暂无评分";
                    if (extend != null && extend.getString("service_score") != null) {
                        score = extend.getString("service_score");
                    }
                    JSONObject shopObj = new JSONObject();
                    shopObj.put("firstAddName", firstAddName);
                    shopObj.put("secondAddName", secondAddName);
                    shopObj.put("thirdAddName", thirdAddName);
                    shopObj.put("shopName", shopName);
                    shopObj.put("shopAddress", shopAddress);
                    shopObj.put("score", score);
                    System.out.println("shopNumber: " + shopNumber + ", shopName: " + shopName + ", score: " + score);
                    shopObj.put("detail", shop.toJSONString());

                    shopName = StringUtils.trimAllWhitespace(shopName);
                    String fileName = "data_" + shopNumber + "_" + shopName + "_" + score + ".txt";
                    File file = new File("D:\\data\\shopbox\\" + fileName);
                    FileOutputStream os = null;
                    try {
                        os = new FileOutputStream(file);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        byte[] contentInBytes = shopObj.toString().getBytes();
                        os.write(contentInBytes);
                        os.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        log.info("Capture-getInfo: All Done!");

        return "All Done!";

    }

    //https://cvas.jd.com/locShop/getAllShopLocShopProducts.action?callback=jQuery6972240&skuNum=1&physicalSkuId=2446806&firstAddCode=3&currentPage=3&pageSize=10&origin=1&redemptionCenter=0&sortType=6&sort=asc&rand=1567153466969&_=1567153466969
    public JSONObject get(int firstAddCode, int currentPage) {

        String url = "https://cvas.jd.com/locShop/getAllShopLocShopProducts.action?callback=jQuery6972240&skuNum=1&physicalSkuId=2446806&firstAddCode="+firstAddCode+"&currentPage="+currentPage+"&pageSize=10&origin=1&redemptionCenter=0&sortType=6&sort=asc&rand=1567153466969&_=1567153466969";
        String result = "";
        BufferedReader reader = null;

        try {
            URL realUrl = new URL(url);
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

        result = result.substring(result.indexOf("(") + 1, result.length() - 2);
        JSONObject value = parse(result);

        return value;

    }

    public JSONObject parse(String result) {
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject value = jsonObject.getJSONObject("value");
        return value;
    }



    @PostMapping(value = "getInfoXlsx")
    public String getInfoXlsx(@RequestBody CaptureVo vo) {

        int shopNumber = 0;
        List<List<Object>> rows = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            int currentPage = 1;
            JSONObject object = get(i, currentPage);
            int prePageTotal = (Integer) object.get("storeCount");
            int pageTotal = (int) Math.ceil(prePageTotal/10.0);
            for (int j = 1; j <= pageTotal; j++) {
                JSONObject jsonObject = get(i, j);
                if (jsonObject.get("locShopProductSet") == null) {
                    continue;
                }
                JSONArray result = jsonObject.getJSONArray("locShopProductSet");
                for (int s = 0; s < result.size(); s++) {
                    shopNumber++;

                    JSONObject shop = result.getJSONObject(s);
                    String firstAddName = shop.getString("firstAddName");
                    String secondAddName = shop.getString("secondAddName");
                    String thirdAddName = shop.getString("thirdAddName");
                    String shopName = shop.getString("locShopName");
                    shopName = StringUtils.trimAllWhitespace(shopName);
                    String shopAddress = shop.getString("locShopFullAddr");
                    JSONObject extend = shop.getJSONObject("extend");
                    String score = "暂无评分";
                    if (extend != null && extend.getString("service_score") != null) {
                        score = extend.getString("service_score");
                    }
                    List<Object> row = new ArrayList<>();
                    row.add(shopNumber);
                    row.add(firstAddName);
                    row.add(secondAddName);
                    row.add(thirdAddName);
                    row.add(shopName);
                    row.add(shopAddress);
                    row.add(score);
                    System.out.println(row.toString());
                    row.add(shop.toJSONString());
                    rows.add(row);
                }
            }

        }

        createXlsx(rows);

        log.info("Capture-getInfo: All Done!");

        return "All Done!";

    }

    public void createXlsx(List<List<Object>> rows) {
        String filePath = "D:\\data\\shopbox\\shopData.xlsx";
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
            table.setHead(createListStringHead());
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

    private static List<List<String>> createListStringHead() {
        List<List<String>> heads = new ArrayList<>();
        String[] titles = {"No", "省", "市", "区", "店铺名称", "店铺地址", "评分", "店铺JSON详情"};
        for (int i = 0; i < titles.length; i++) {
            List<String> head = new ArrayList<>();
            head.add(titles[i]);
            heads.add(head);
        }
        return heads;
    }

}
