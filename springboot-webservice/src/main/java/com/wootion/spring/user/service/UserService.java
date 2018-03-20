package com.wootion.spring.user.service;

import com.wootion.spring.user.entity.UserEntity;
import com.wootion.spring.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangXiaoXiang
 * @date 2018/3/19 21:39
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> findlist(UserEntity userEntity){
       return userMapper.getList(userEntity);
    }
}
