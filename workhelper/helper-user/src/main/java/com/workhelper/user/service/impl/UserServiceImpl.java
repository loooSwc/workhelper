package com.workhelper.user.service.impl;

import com.workhelper.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Override
    @RequestMapping(value = "/user")
    public String user() {
        System.out.println("user模块service内容");
        return null;
    }
}
