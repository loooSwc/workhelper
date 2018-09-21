package com.workhelper.user.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "helper-user")
public interface UserService {
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    String user();

}
