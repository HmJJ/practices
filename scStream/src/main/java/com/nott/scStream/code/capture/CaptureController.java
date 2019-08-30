package com.nott.scStream.code.capture;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nott.scStream.code.capture.vo.CaptureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
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
                System.out.println(result.toJSONString());
                String fileName = "data_" + i + "_" + j + ".txt";
                File file = new File("D:\\data\\shopbox\\" + fileName);
                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(file);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    byte[] contentInBytes = result.toString().getBytes();
                    os.write(contentInBytes);
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
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
                    System.out.println(map.get("key"));
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

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject value = jsonObject.getJSONObject("value");

        return value;

    }

}
