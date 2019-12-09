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
 * @Date: created in 2019/12/5 16:03
 * @Modified By:
 **/
@RestController
@RequestMapping(value = "taobaoCapture")
public class TaobaoCaptureController {

    private static Logger log = LoggerFactory.getLogger(TaobaoCaptureController.class);


    @PostMapping(value = "getInfoXlsx")
    public String getInfoXlsx(@RequestBody CaptureVo vo) {

        int shopNumber = 0;
        List<List<Object>> rows = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            int currentPage = 1;
            JSONObject object = get(i, currentPage);
            int prePageTotal = (Integer) object.get("total");
            int pageTotal = (int) Math.ceil(prePageTotal/10.0);
            for (int j = 1; j <= pageTotal; j++) {
                JSONObject jsonObject = get(i, j);
                if (jsonObject.get("stores") == null) {
                    continue;
                }
                JSONArray result = jsonObject.getJSONArray("stores");
                for (int s = 0; s < result.size(); s++) {
                    shopNumber++;

                    List<Object> row = new ArrayList<>();
                    JSONObject shop = result.getJSONObject(s);
                    String id = shop.getString("id");row.add(id);
                    String address = shop.getString("address");row.add(address);
                    String busroutes = shop.getString("busroutes");row.add(busroutes);
                    String city = shop.getString("city");row.add(city);
                    String comments = shop.getString("comments");row.add(comments);
                    String distance = shop.getString("distance");row.add(distance);
                    String district = shop.getString("district");row.add(district);
                    String dsr = shop.getString("dsr");row.add(dsr);
                    String name = shop.getString("name");row.add(name);
                    String posx = shop.getString("posx");row.add(posx);
                    String posy = shop.getString("posy");row.add(posy);
                    String praiserate = shop.getString("praiserate");row.add(praiserate);
                    String prov = shop.getString("prov");row.add(prov);
                    String rateCount = shop.getString("rateCount");row.add(rateCount);
                    String store_type = shop.getString("store_type");row.add(store_type);
                    String tel = shop.getString("tel");row.add(tel);
                    String url = shop.getString("url");row.add(url);
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

    //https://map.taobao.com/item/api/itemStoreList.do?locType=current&_input_charset=utf-8&source=map_itemdetail&pageSize=20&pageNo=1&areaCode=310115&itemId=604699475944&tbToken=eee17f6759e3a&callback=reqwest_1575532145311
    public JSONObject get(int firstAddCode, int currentPage) {

        String url = "https://map.taobao.com/item/api/itemStoreList.do?locType=current&_input_charset=utf-8&source=map_itemdetail&pageSize=10&pageNo="+ currentPage +"&areaCode=310115&itemId=604699475944&tbToken=eee17f6759e3a";
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

        JSONObject value = JSONObject.parseObject(result);

        return value;

    }

    public void createXlsx(List<List<Object>> rows) {
        String filePath = "D:\\data\\shopbox\\taobao.xlsx";
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
        String[] titles = {"id", "name", "city", "address", "busroutes", "comments", "distance", "district", "dsr", "posx", "posy", "praiserate", "prov", "rateCount", "store_type", "tel", "url"};
        for (int i = 0; i < titles.length; i++) {
            List<String> head = new ArrayList<>();
            head.add(titles[i]);
            heads.add(head);
        }
        return heads;
    }

}
