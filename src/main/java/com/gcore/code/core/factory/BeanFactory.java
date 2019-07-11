package com.gcore.code.factory;

import com.gcore.code.contatiner.MyContainer;
import com.gcore.code.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanFactory {
    private MyContainer container;
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    public void init(String folderPath){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    }
}
