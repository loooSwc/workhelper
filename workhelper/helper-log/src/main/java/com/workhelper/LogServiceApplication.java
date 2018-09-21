package com.workhelper;
import com.workhelper.common.dao.jpa.RepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: cxy@acmtc.com
 * \* Date: 2018/9/4
 * \* Time: 17:19
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@EntityScan("com.workhelper")
@SpringBootApplication(scanBasePackages = {"com.workhelper"})
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryFactoryBeanClass = RepositoryFactoryBean.class, basePackages = {"com.workhelper"})
@EnableFeignClients
public class LogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogServiceApplication.class, args);
    }
}