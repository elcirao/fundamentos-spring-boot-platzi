package com.fundamentos.platzi.fundamentos.configuration;

import com.fundamentos.platzi.fundamentos.bean.MyBean;
import com.fundamentos.platzi.fundamentos.bean.MyBean2Implement;
import com.fundamentos.platzi.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.platzi.fundamentos.bean.MyBeanWithDependencyImplement;
import com.fundamentos.platzi.fundamentos.bean.MyOperation;
import com.fundamentos.platzi.fundamentos.bean.MyOperationImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation() {
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation beanOperationSum() {
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationImplWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
