package ru.pvorobey.checkrunnerconsole.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("ru.pvorobey.checkrunnerconsole")
@PropertySource("classpath:application.properties")
public class DBConfig {

    //читает проперти файл.получаем из нее значения
    @Autowired
    Environment environment;

    private final String driverName = "driver-name";
    private final String url = "jdbc-url";
    private final String username = "jdbc-username";
    private final String password = "jdbc-password";

    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(driverName)));
        dataSource.setUrl(environment.getProperty(url));
        dataSource.setUsername(environment.getProperty(username));
        dataSource.setPassword(environment.getProperty(password));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return  new JdbcTemplate(dataSource());
    }

}
