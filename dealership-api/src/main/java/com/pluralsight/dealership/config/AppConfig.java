package com.pluralsight.dealership.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/your_db");  // Replace with your DB URL
        dataSource.setUsername("yourUsername");  // Replace with your DB username
        dataSource.setPassword("yourPassword");  // Replace with your DB password
        return dataSource;
    }
}
