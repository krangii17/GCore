package com.gcore.code.test;

import com.gcore.code.core.config.BeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanPostProccessorImpl implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        logger.info("Custom bean post processor works");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        logger.info("Custom bean post processor works");
        return bean;
    }
}
