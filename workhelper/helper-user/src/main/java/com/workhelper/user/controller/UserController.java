package com.workhelper.user.controller;

import com.workhelper.user.dao.UserDao;
import com.workhelper.user.model.BaseUser;
import com.workhelper.log.service.LogService;
import com.workhelper.user.model.com.workhelper.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RefreshScope
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/hi")
    @ResponseBody
    public String hi()throws Exception{
        System.out.println("user调用成功" + environment.getProperty("test"));
//        userService.user();
//        List<BaseUser> baseUser = userDao.find("from BaseUser where id=?","0eeab1c4056711e8b986525400b05776");
//        System.out.println(baseUser.get(0).getAccount());
//        restTemplate.getForObject("http://helper-log/log",String.class);
//        logService.log();
        return null;
    }
}
