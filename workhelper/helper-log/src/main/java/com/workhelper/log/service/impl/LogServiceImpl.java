package com.workhelper.log.service.impl;

import com.workhelper.log.service.LogService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: cxy@acmtc.com
 * \* Date: 2018/9/4
 * \* Time: 17:06
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Transactional
@Service
@Primary
public class LogServiceImpl implements LogService {
    public void addLog(String operation) throws Exception {
        System.out.println(operation);
    }
}