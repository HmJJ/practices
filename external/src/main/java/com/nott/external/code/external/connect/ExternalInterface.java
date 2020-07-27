package com.nott.external.code.external.connect;

import com.nott.external.code.external.connect.vo.ParamVo;
import com.nott.external.code.external.connect.vo.UrlVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/4/29 16:51
 * @Modified By:
 **/
@Service
public class ExternalInterface {

    public static Map<String, UrlVo> urls = new ConcurrentHashMap<>();

    public static final String IP = "https://www.wayonsys.com";
    public static final Integer PORT = 9443;

    public static final String IP_ADDR = "https://www.wayonsys.com:9443";

    static{
        // 登录接口
        urls.put("login", new UrlVo("login", "登录",IP_ADDR + "/login", "POST", true, null));

        // 业务接口
        urls.put("getRiskInfo", new UrlVo("riskInfo", "查询隐患信息", IP_ADDR + "/issue", "GET", false, null));
        urls.put("getRiskAnalysisInfo", new UrlVo("riskAnalysisInfo", "查询风险研判信息", IP_ADDR + "/analysis", "GET", false, null));
    }

    public UrlVo getUrl(String type) {
        return urls.get(type);
    }

    private static List<ParamVo> defaultEmptyParams() {
        List<ParamVo> params = new ArrayList<>();
        ParamVo paramVo = new ParamVo("params", "");
        params.add(paramVo);
        return params;
    }
    private static List<ParamVo> defaultPageParams() {
        List<ParamVo> params = new ArrayList<>();
        ParamVo paramVo1 = new ParamVo("page", "1");
        ParamVo paramVo2 = new ParamVo("limit", "10");
        params.add(paramVo1);
        params.add(paramVo2);
        return params;
    }

}
