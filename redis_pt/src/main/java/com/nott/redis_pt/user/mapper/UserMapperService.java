package com.nott.redis_pt.user.mapper;

import com.nott.redis_pt.user.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperService {

    private static final Logger logger = LoggerFactory.getLogger(UserMapperService.class);

    @Autowired
    private SqlSession sqlSession;

    public List<UserVo> search(UserVo userVo) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<UserVo> results = mapper.findUserList(userVo);
        return results;
    }

}
