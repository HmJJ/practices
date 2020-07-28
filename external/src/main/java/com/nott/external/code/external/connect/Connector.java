package com.nott.external.code.external.connect;

import com.alibaba.fastjson.JSONObject;
import com.nott.external.code.external.connect.vo.ParamVo;
import com.nott.external.code.external.connect.vo.UrlVo;
import com.nott.external.code.utils.SHA;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/4/29 17:38
 * @Modified By:
 **/
@Service
public class Connector {

    public static Logger logger = LoggerFactory.getLogger(Connector.class);

    @Autowired
    private ExternalInterface externalInterface;

    private String login() {
        JSONObject params = new JSONObject();
        params.put("type", "account");
        params.put("j_username", "sfwy2015");
        params.put("j_password", "sfwy52038688");
        String cookie = getCookie("login", params.toJSONString());
        return cookie;
    }

    public String execute(String type, List<ParamVo> params) {
        UrlVo urlVo = externalInterface.getUrl(type);
        if (urlVo == null) {
            return null;
        }
//        String cookie = login();
        String cookie = null;
        String result = "";
        if (urlVo.isNeedParams() && params != null && params.size()>0) {
            urlVo.setParams(params);
        }
        if ("POST".equals(urlVo.getMethod())) {
            result = sendPost(urlVo.getUrl(), urlVo.getParams(), cookie);
        } else {
            result = sendGet(urlVo.getUrl(), urlVo.getParams(), cookie);
        }
        return result;
    }

    public String sendPost(String urlParam, List<ParamVo> params, String cookie) {
        PostMethod postMethod = new PostMethod(urlParam);
        if (params != null) {
            for (ParamVo vo : params) {
                postMethod.addParameter(vo.getKey(), vo.getValue());
            }
        }
        String result = getResult(postMethod, cookie);
        return result;
    }

    public String sendGet(String url, List<ParamVo> params, String cookie) {
        if (params != null) {
            StringBuilder urlParam = new StringBuilder(url);
            urlParam.append("?");
            int paramCount = params.size() - 1;
            for (ParamVo paramVo : params) {
                urlParam.append(paramVo.getKey());
                if (StringUtils.isNotBlank(paramVo.getValue())) {
                    urlParam.append("=");
                    urlParam.append(paramVo.getValue());
                }
                if (paramCount > 0) {
                    urlParam.append("&");
                    paramCount--;
                }
            }
            url = urlParam.toString();
        }
        GetMethod getMethod = new GetMethod(url);
        String result = getResult(getMethod, cookie);
        return result;
    }

    private String getResult(HttpMethodBase method, String cookie) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        method.addRequestHeader("Cookie", cookie);
        Map<String, String> map = SHA.getResult(null, "300000");
        System.out.println(map.toString());
        method.setRequestHeader("token", map.get("token"));
        method.setRequestHeader("saltDate", "300000");
        String result = "";
        try {
            httpClient.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (HttpException e1) {
            logger.info(e1.getCause().getMessage());
            throw new ServiceException("操作失败");
        } catch (IOException e2) {
            logger.info(e2.getCause().getMessage());
            throw new ServiceException("操作失败");
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    private String getCookie(String type, String params) {
        UrlVo urlVo = externalInterface.getUrl(type);
        PostMethod postMethod = new PostMethod(urlVo.getUrl());
        postMethod.addParameter("params", params);
        String cookie = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        try {
            httpClient.executeMethod(postMethod);
            Cookie[] cookies = httpClient.getState().getCookies();
            for (Cookie c : cookies) {
                cookie = c.toString();
            }
        } catch (HttpException e1) {
            logger.info(e1.getCause().getMessage());
            throw new ServiceException("操作失败");
        } catch (IOException e2) {
            logger.info(e2.getCause().getMessage());
            throw new ServiceException("操作失败");
        } finally {
            postMethod.releaseConnection();
        }
        return cookie;
    }

}
