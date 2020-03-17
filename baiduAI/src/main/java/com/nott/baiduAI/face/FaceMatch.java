package com.nott.baiduAI.face;

import com.alibaba.fastjson.JSONArray;
import com.nott.baiduAI.face.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/3/17 9:51
 * @Modified By:
 **/
@Service
public class FaceMatch {

    @Autowired
    private AuthService authService;
//    @Autowired
//    private StringRedisService stringRedisService;

    public String faceMatch(JSONArray array) {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {

            String param = array.toString();

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "";
//                    stringRedisService.get("baidu_face_token");
//            if (accessToken == null) {
                accessToken = authService.getAuth();
//                stringRedisService.set("baidu_face_token", accessToken, 2592000);2592000
//            }

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
