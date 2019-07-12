package com.gcore.code.test;

import com.gcore.code.core.config.BeanPostProcessor;

public class BeanPostProccessorImpl implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }
}
