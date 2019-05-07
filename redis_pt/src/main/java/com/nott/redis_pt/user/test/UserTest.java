package com.nott.redis_pt.user.test;

import com.nott.redis_pt.boot.BootApplication;
import com.nott.redis_pt.user.mapper.UserMapperService;
import com.nott.redis_pt.user.service.UserService;
import com.nott.redis_pt.user.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootApplication.class})
public class UserTest {

    private static final Logger log = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapperService userMapperService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testListUser() {
        UserVo userVo = new UserVo();
        userVo.setName("nott");
        userVo.setAge(23);
        userService.create(userVo);

        List<UserVo> userVos = new ArrayList<>();
        userVos = redisTemplate.opsForList().range("userList", 0, -1);

        if(userVos.size() == 0) {
            userVos = userMapperService.search(new UserVo());
        }

        for (UserVo vo : userVos) {
            log.info(vo.toString());
        }

    }

}
