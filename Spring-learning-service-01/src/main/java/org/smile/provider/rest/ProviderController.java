package org.smile.provider.rest;

import org.smile.model.User;
import org.smile.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: org.smile.provider.rest
 * @Description:
 * @author: liuxin
 * @date: 2017/12/21 下午4:08
 */
@RestController
public class ProviderController {
    @Autowired
    UserService userService;

    @GetMapping(value = "user",produces = "application/json")
    public User getUser(){
        return userService.getUser();
    }
}
