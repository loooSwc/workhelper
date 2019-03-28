package com.workhelper.log.controller;

import com.workhelper.log.dao.LogDao;
import com.workhelper.log.model.BaseSysLog;
import com.workhelper.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: c.9u@outlook.com
 * \* Date: 2018/9/4
 * \* Time: 17:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@Transactional
public class LogController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogDao logDao;
    @RequestMapping(value = "/log")
    @ResponseBody
    public String hi()throws Exception{
        System.out.print("调用log成功");
        userService.user();
        BaseSysLog baseSysLog = new BaseSysLog();
        baseSysLog.setUserId("1234");
        logDao.saveOrUpdate(baseSysLog);
        return null;
    }
}