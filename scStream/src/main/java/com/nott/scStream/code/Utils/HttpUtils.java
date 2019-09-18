package com.nott.scStream.code.Utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/18 9:40
 * @Modified By:
 **/
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 对指定url发送请求，以JSON格式传参接参
     * @param url
     * @param params
     * @return
     */
    public JSONObject getJSONByUrl(String url, JSONObject params) {
        PrintWriter writer = null;
        BufferedReader reader = null;
        JSONObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");


            writer = new PrintWriter(conn.getOutputStream());
            writer.print(params.toJSONString());
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            jsonObject = JSONObject.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
//            log.info(e.getCause().getMessage());
        }
        return jsonObject;
    }

}
