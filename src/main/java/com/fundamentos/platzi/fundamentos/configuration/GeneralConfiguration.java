package com.fundamentos.platzi.fundamentos.configuration;

import com.fundamentos.platzi.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.platzi.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.platzi.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    @Value("${value.lastname}")
    private String lastname;

    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithProperties testFunction() {
        return new MyBeanWithPropertiesImplement(name, lastname);
    }
}
