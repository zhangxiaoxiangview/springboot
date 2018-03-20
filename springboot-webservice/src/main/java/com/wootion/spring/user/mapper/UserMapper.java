package com.wootion.spring.user.mapper;

import com.wootion.config.mybatisconfig.BootRepository;
import com.wootion.spring.user.entity.UserEntity;

import java.util.List;

/**
 * @author ZhangXiaoXiang
 * @date 2018/3/19 20:48
 */
@BootRepository
public interface UserMapper {
    /**
     * 查询用户信息
     * @param username 查询条件
     * @return List<UserEntity>
     */
    List<UserEntity> getList(String username);
}
