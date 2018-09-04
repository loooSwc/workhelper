package com.workhelper.common.configure;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: cxy@acmtc.com
 * \* Date: 2018/8/8
 * \* Time: 18:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
public class DataSourceConfiguration {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class}")
    private String driverClass;

    @Bean // 声明其为Bean实例
    @Primary // 在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);

        dataSource.setDefaultAutoCommit(true);
        dataSource.setTimeBetweenEvictionRunsMillis(3600000);
        dataSource.setMinEvictableIdleTimeMillis(3600000);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
        List<Filter> filters = new ArrayList<Filter>(){
            {
                StatFilter statFilter = new StatFilter();
                statFilter.setSlowSqlMillis(30000);
                statFilter.setLogSlowSql(true);
                statFilter.setMergeSql(true);
                add(statFilter);
                WallFilter wallFilter = new WallFilter();
                WallConfig config = new WallConfig();
                config.setConditionAndAlwayTrueAllow(true);
                config.setDir("META-INF/druid/wall/mysql");
                config.init();
                wallFilter.setConfig(config);
                add(wallFilter);
                Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
                slf4jLogFilter.setResultSetLogEnabled(true);
                slf4jLogFilter.setStatementExecutableSqlLogEnable(true);
                add(slf4jLogFilter);
            }
        };

        dataSource.setProxyFilters(filters);
        return dataSource;
    }
}