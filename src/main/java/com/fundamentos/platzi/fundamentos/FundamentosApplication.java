package com.fundamentos.platzi.fundamentos;

import com.fundamentos.platzi.fundamentos.bean.MyBean;
import com.fundamentos.platzi.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.platzi.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.platzi.fundamentos.component.ComponentDependency;
import com.fundamentos.platzi.fundamentos.pojo.UserPojo;
import org.apache.catalina.User;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log Logger = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
    }

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        myBeanWithDependency.printWithDependency();
        componentDependency.saludar();
        myBean.print();
        System.out.println(myBeanWithProperties.fullname());
        System.out.println(userPojo.getEmail() +"-"+userPojo.getPassword()+"-"+userPojo.getAge());
        Logger.error("Error del aplicativo");
    }
}
