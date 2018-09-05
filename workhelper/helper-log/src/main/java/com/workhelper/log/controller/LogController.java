package com.workhelper.log.controller;

import com.workhelper.log.service.LogService;
import com.workhelper.user.model.com.workhelper.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: cxy@acmtc.com
 * \* Date: 2018/9/4
 * \* Time: 17:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class LogController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/log")
    @ResponseBody
    public String hi()throws Exception{
        System.out.print("调用log成功");
        userService.user();
        return null;
    }
}