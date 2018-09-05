package com.workhelper.chat.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "helper-chat")
public interface ChatService {
    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    String chat();
}
