package com.endava.ap.lotery.service.impl;

import org.junit.Before;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import javax.transaction.Transactional;

@RunWith(JUnitPlatform.class)
@PropertySource("classpath:application.properties")
public class AbstractTestDatabaseInit {

    @Autowired
    Environment environment;

//    @Bean
//    public DataSource postgresqlDatasource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("jdbc.postgresql.org.Driver");
//        dataSource.setUrl(environment.getProperty("datasource.url"));
//        dataSource.setUsername(environment.getProperty("datasource.username"));
//        dataSource.setPassword(environment.getProperty("datasource.password"));
//        return dataSource;
//    }

    private static final String FIRSTNAME = "Lucky";
    private static final String LASTNAME = "Winner";
    private static final String EMAIL = "lotery@endava.com";

    @Before
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void init() {
        JdbcTemplate jdbcTemplate = extractJdbcTemplate();
        jdbcTemplate.update("insert  into t_participant values ?,?,?,?", FIRSTNAME, LASTNAME, EMAIL, null);

    }

    public JdbcTemplate extractJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return new JdbcTemplate(dataSource);
    }
}
