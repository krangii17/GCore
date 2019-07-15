package com.gcore.code.core.context;

import com.gcore.code.core.config.BeanPostProcessor;

public interface ApplicationContext {
    void initBeanPostProcessor();

    void addToBeanPostProcessorContainer(BeanPostProcessor postProcessor);

    Object getBeanByName(String name);
}
