package com.gcore.code.context;

import com.gcore.code.factory.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
    private BeanFactory beanFactory;

    public ApplicationContext(String scanPackage){
        logger.info("_______ApplicationContext_______");
        beanFactory = new BeanFactory();
        beanFactory.init("com.gcore.code");
    }

}
