package com.nott.security.common.exception;

import com.nott.security.common.contract.ResultVo;
import com.nott.security.common.security.SecurityUtils;
import com.nott.security.common.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:44
 * @Modified By:
 **/
@ControllerAdvice
public class DefaultExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    public DefaultExceptionHandler() {
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler({ServiceException.class})
    public ResultVo handleServiceException(ServiceException e) {
        String username = (String)SecurityUtils.getCurrentUserLogin().get();
        log.error(ExceptionUtils.getErrorInfo(e));
        log.error("ServiceException error{} username:{}", e.getMessage(), username);
        ResultVo resultVo = new ResultVo(e.getStatusCode(), e.getMessage(), e.getData());
        return resultVo;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler({AuthException.class})
    public ResponseEntity handleAuthException(AuthException e) {
        log.error("AuthException error:{}", e.getMessage());
        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity handleAuthException(AuthenticationException e) {
        log.error("AuthenticationException error:{}", e.getMessage());
        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResultVo handleException(Exception e) {
        String username = (String) SecurityUtils.getCurrentUserLogin().get();
        log.error("Exception username:{}, error:{}", username, ExceptionUtils.getErrorInfo(e));
        ResultVo resultVo = new ResultVo("9000", e.getMessage());
        return resultVo;
    }
}
