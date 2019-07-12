package com.gcore.code.core.factory;

import com.gcore.code.core.config.BeanPostProcessor;
import com.gcore.code.core.contatiner.MyContainer;

public interface BeanFactory {
    void init(String folderPath);
    void injectBeanFactoryAwaresBeans();
    void setAllFieldsContext();
    void initializeBeans();
    void addToBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    MyContainer getContainer();
}
