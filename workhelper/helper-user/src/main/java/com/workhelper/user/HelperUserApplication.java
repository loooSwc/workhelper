package com.workhelper.user;

import com.workhelper.common.dao.jpa.RepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.workhelper"})
@EnableEurekaClient
@EnableJpaRepositories(repositoryFactoryBeanClass = RepositoryFactoryBean.class, basePackages = {"com.workhelper"})
public class HelperUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelperUserApplication.class, args);
    }
}
