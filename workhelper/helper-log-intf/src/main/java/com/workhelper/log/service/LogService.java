package com.workhelper.log.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "helper-log")
public interface LogService {
    @RequestMapping(value = "/addLog/{operation}",method = RequestMethod.GET)
    public void addLog(@PathVariable("operation") String operation) throws Exception;
}
