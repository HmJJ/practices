package com.nott.redis_pt.user.service;

import com.nott.redis_pt.user.entity.User;
import com.nott.redis_pt.user.repository.UserRepository;
import com.nott.redis_pt.user.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private UserRepository repository;

    public User find(Long id) {
        User user = repository.findById(id).get();
        return user;
    }

    public Long create(UserVo userVo) {

        User user = new User();

        BeanUtils.copyProperties(userVo, user);
        user = repository.save(user);

        return user.getId();

    }

    public void modify(UserVo userVo) {

        User user = find(userVo.getId());

        BeanUtils.copyProperties(userVo, user);
        user = repository.save(user);

    }

    public void delete(Long id) {
        User user = find(id);
        user.setDelete(true);
        repository.save(user);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

}
