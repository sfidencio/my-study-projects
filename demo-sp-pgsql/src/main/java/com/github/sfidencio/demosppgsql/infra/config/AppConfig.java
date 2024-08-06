package com.github.sfidencio.demosppgsql.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/*
 * Eu nem precisaria de definir um JdbcTemplate customizado, pois o Spring Boot já faz isso por mim.
 * Entretanto quero ter o controle de como as transações são gerenciadas, por isso estou definindo um
 * DataSourceTransactionManager.
 */

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private String connectionTimeout;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private String maximumPoolSize;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private String idleTimeout;

    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;

    @Value("${spring.datasource.hikari.auto-commit}")
    private String autoCommit;

    @Value("${spring.datasource.hikari.jdbc-url}")
    private String jdbcUrl;

    @Bean
    public DataSource dataSource() {
        // Aqui eu poderia configurar um DataSource customizado, mas estou usando o HikariCP que é o padrão do Spring Boot.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //pega essas configurações do application.yaml
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //hikariCP - define o pool de conexões
        Properties properties = new Properties();
        properties.setProperty("spring.datasource.hikari.connection-timeout", connectionTimeout);
        properties.setProperty("spring.datasource.hikari.maximum-pool-size", maximumPoolSize);
        properties.setProperty("spring.datasource.hikari.idle-timeout", idleTimeout);
        properties.setProperty("spring.datasource.hikari.pool-name", poolName);
        properties.setProperty("spring.datasource.hikari.auto-commit", autoCommit);
        properties.setProperty("spring.datasource.hikari.jdbc-url", jdbcUrl);
        dataSource.setConnectionProperties(properties);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
