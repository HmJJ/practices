package com.nott.baiduAI.face;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/3/17 10:02
 * @Modified By:
 **/
@Component
public class AuthService {

    @Value("${aip.face.appId}")
    private String APP_ID;
    @Value("${aip.face.key}")
    private String APP_KEY;
    @Value("${aip.face.secret}")
    private String SECRET_KEY;

    /**
     * 获取权限token，该token的过期时间为30天
     * @return
     */
    public String getAuth() {
        return getAuth(APP_KEY, SECRET_KEY);
    }

    public String getAuth(String ak, String sk) {
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
            + "grant_type=client_credentials"
            + "&client_id=" + ak
            + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.println("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

}
