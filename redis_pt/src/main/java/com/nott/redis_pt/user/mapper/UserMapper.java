package com.nott.redis_pt.user.mapper;

import com.nott.redis_pt.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserVo> findUserList(UserVo vo);
    Integer findUserListCount(UserVo vo);

}
