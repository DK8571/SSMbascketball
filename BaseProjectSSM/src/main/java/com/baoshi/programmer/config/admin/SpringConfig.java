package com.baoshi.programmer.config.admin;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan({"com.baoshi.programmer"})
@PropertySource("classpath:db.properties")
@Import({JdbcConfig.class,MyBatisConfig.class,AlipayConfig.class})
@EnableTransactionManagement
public class SpringConfig {
}
