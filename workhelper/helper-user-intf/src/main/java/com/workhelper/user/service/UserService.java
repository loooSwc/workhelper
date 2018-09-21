package com.workhelper.user.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "helper-user")
@Primary
public interface UserService {
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    String user();

}
