package com.fundamentos.platzi.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log Logger = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        Logger.info("Hemos ingresado al metodo printWithDependency");
        int number = 1;
        Logger.debug("el numero enviado como param a la dependencia operacion es: " + number);
        System.out.println(myOperation.sum(number));
        System.out.println("hola desde la implementacion de un bean con dependencia");
    }
}
