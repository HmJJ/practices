package com.nott.security.common.security;

import com.nott.security.common.cache.RedisService;
import com.nott.security.common.contract.ResultVo;
import com.nott.security.common.exception.AuthException;
import com.nott.security.common.exception.ServiceException;
import com.nott.security.common.utils.AesEncrypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 11:26
 * @Modified By:
 **/
@Component
public class SecurityUtils {

    @Autowired
    @Qualifier("feignTemplate")
    private RestTemplate feignTemplate;
    public static final String REDIS_KEY_PREFIX = "my_account_";
    public static final Long PLATFORM = 0L;
    private static RedisService redisService;

    public SecurityUtils() {}

    @Autowired
    public void setRedisService(RedisService redisService) {
        SecurityUtils.redisService = redisService;
    }

    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication()).map((authentication) -> {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails)authentication.getPrincipal();
                return springSecurityUser.getUsername();
            } else {
                return authentication.getPrincipal() instanceof String ? (String)authentication.getPrincipal() : null;
            }
        });
    }

    public String getCurrentToken() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
            return details.getTokenValue();
        } else {
            return null;
        }
    }

    public static Optional<String> getCurrentClientIdLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication()).map((authentication) -> {
            if (authentication.getPrincipal() instanceof UserDetails) {
                return ((OAuth2Authentication)authentication).getOAuth2Request().getClientId();
            } else if (authentication.getPrincipal() instanceof String) {
                return authentication.getPrincipal().equals("anonymousUser") ? null : ((OAuth2Authentication)authentication).getOAuth2Request().getClientId();
            } else {
                return null;
            }
        });
    }

    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (Boolean)Optional.ofNullable(securityContext.getAuthentication()).map((authentication) -> {
            return authentication.getAuthorities().stream().noneMatch((grantedAuthority) -> {
                return grantedAuthority.getAuthority().equals("ROLE_ANONYMOUS");
            });
        }).orElse(false);
    }

    public static MyAccountInfo getMyAccountInfo() {
        Optional<String> client = getCurrentClientIdLogin();
        Optional<String> login = getCurrentUserLogin();
        if (!client.isPresent()) {
            return null;
        } else if (!login.isPresent()) {
            return null;
        } else {
            String username = (String)login.get();
            String clientId = (String)client.get();
            if (StringUtils.isBlank(username)) {
                throw new AuthException("获取账号信息错误，您还没登录！");
            } else {
                String key = "my_account_" + clientId + username;
                MyAccountInfo myAccountInfo = (MyAccountInfo)redisService.get(key);
                return myAccountInfo;
            }
        }
    }

    public static void setMyAccountInfo(String username, MyAccountInfo accountInfo) {
        String clientId = (String)getCurrentClientIdLogin().get();
        String key = "my_account_" + clientId + username;
        redisService.set(key, accountInfo);
        redisService.setExpire(key, 36000000L);
    }

    public static void removeMyAccountInfo() {
        String cleintId = (String)getCurrentClientIdLogin().get();
        Optional<String> login = getCurrentUserLogin();
        if (login.isPresent()) {
            String username = (String)login.get();
            if (!StringUtils.isBlank(username)) {
                String key = "my_account_" + cleintId + username;
                redisService.delete(key);
            }
        }
    }

    public String getAccessToken(String username, String password) {
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        map.put("client_id", "apiClient");
        Map resultMap = this.postForObject("http://gateway/auth/login", map);
        String token = (String)resultMap.get("access_token");
        this.findMyAccountInfo(token);
        return token;
    }

    public String getTokenByOathCode(String oauthCode) {
        String code = AesEncrypt.decrypt(oauthCode);
        String[] codes = code.split(",");
        return this.getAccessToken(codes[1], "wOs@1003");
    }

    public void findMyAccountInfo(String token) {
        this.postForObject("http://system/api/user/findMyAccount", (Object)null, token);
    }

    public String getOauthCode() {
        String token = this.getCurrentToken();
        ResultVo resultVo = this.postForObject("http://platform/api/rpc/getOauthCode", (Object)null, token);
        String code = (String)resultVo.getData();
        return code;
    }

    public ResultVo postForObject(String url, Object body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setBearerAuth(token);
        HttpEntity requestEntity = new HttpEntity(body, headers);
        ResultVo resultVo = (ResultVo)this.feignTemplate.postForObject(url, requestEntity, ResultVo.class, new Object[0]);
        if (!"1000".equals(resultVo.getCode())) {
            throw new ServiceException(resultVo.getMessage());
        } else {
            return resultVo;
        }
    }

    public Map<String, Object> postForObject(String url, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity requestEntity = new HttpEntity(body, headers);
        Map<String, Object> object = (Map)this.feignTemplate.postForObject(url, requestEntity, Map.class, new Object[0]);
        return object;
    }
}
