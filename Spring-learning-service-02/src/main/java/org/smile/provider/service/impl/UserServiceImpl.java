package org.smile.provider.service.impl;

import org.smile.model.User;
import org.smile.provider.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Package: org.smile.provider.service.UserServiceImpl
 * @Description:
 * @author: liuxin
 * @date: 2017/12/21 下午4:06
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public User getUser() {
        return new User("测试人员-02",21,"1.0.0");
    }
}
