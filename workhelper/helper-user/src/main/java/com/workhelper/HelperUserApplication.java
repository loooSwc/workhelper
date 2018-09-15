package com.workhelper;

import com.workhelper.common.dao.jpa.RepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.workhelper"})
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryFactoryBeanClass = RepositoryFactoryBean.class, basePackages = {"com.workhelper"})
@EnableFeignClients
public class HelperUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelperUserApplication.class, args);
    }
}
