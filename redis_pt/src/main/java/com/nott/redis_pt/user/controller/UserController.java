package com.nott.redis_pt.user.controller;


import com.nott.redis_pt.contants.ResultVo;
import com.nott.redis_pt.user.entity.User;
import com.nott.redis_pt.user.mapper.UserMapperService;
import com.nott.redis_pt.user.service.UserService;
import com.nott.redis_pt.user.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapperService mapperService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "search")
    public ResultVo search(@RequestBody UserVo userVo) {

        try {

            List<UserVo> userVos = mapperService.search(userVo);

            for (UserVo vo : userVos) {
                redisTemplate.opsForList().rightPush("userList", vo);
            }


            ResultVo resultVo= new ResultVo(ResultVo.API_SUCCESS, userVos);
            return resultVo;
        } catch (Exception e) {
            logger.info(e.getMessage());
            ResultVo resultVo= new ResultVo(ResultVo.API_ERROR, e.getMessage());
            return resultVo;
        }
    }

    @PostMapping(value = "find")
    public ResultVo find(@RequestBody UserVo userVo) {

        try {
            if (userVo.getId() == null) {
                ResultVo resultVo= new ResultVo(ResultVo.API_ERROR, "用户id不能为空");
                return resultVo;
            }

            User user = userService.find(userVo.getId());

            ResultVo resultVo= new ResultVo(ResultVo.API_SUCCESS, user);
            return resultVo;
        } catch (Exception e) {
            logger.info(e.getMessage());
            ResultVo resultVo= new ResultVo(ResultVo.API_ERROR, e.getMessage());
            return resultVo;
        }
    }

}
