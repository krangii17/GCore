package com.gcore.code.core.factory;

import com.gcore.code.core.contatiner.MyContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);
    private MyContainer container;

    public void init(String folderPath) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    }
}
