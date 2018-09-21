package com.workhelper.user.service.impl;

import com.workhelper.user.dao.UserDao;
import com.workhelper.user.model.BaseUser;
import com.workhelper.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Service
@Transactional
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @RequestMapping(value = "/user")
    public String user() {
        System.out.println("user模块service内容");
        BaseUser user = new BaseUser();
        user.setAccount("Account");
        userDao.saveOrUpdate(user);
        return null;
    }
}
