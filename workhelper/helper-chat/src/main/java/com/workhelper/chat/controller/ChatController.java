package com.workhelper.chat.controller;

import com.workhelper.chat.service.ChatService;
import com.workhelper.user.model.com.workhelper.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/user")
    public String hi()throws Exception{
        System.out.print("chat调用成功");
//        System.out.println(baseUser.get(0).getAccount());
//        restTemplate.getForObject("http://helper-log/log",String.class);
        userService.user();
        return null;
    }
}
