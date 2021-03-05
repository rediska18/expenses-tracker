package com.application.expensestracker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 @author Ярослав
 @date 05.03.2021
 @version 1.0
 */
@Configuration
public class ApplicationConfiguration {

    @Lazy
    @Bean(name = "dataSource")
    public DataSource prepareDataSource (){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:db/h2/schema.ddl")
                .build();
    }

    @Bean
    @Resource(name = "dataSource")
    public JdbcTemplate prepareJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
