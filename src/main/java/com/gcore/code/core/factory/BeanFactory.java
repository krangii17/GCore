package com.gcore.code.core.factory;

import com.gcore.code.core.config.BeanPostProcessor;
import com.gcore.code.core.contatiner.MyContainer;
import com.gcore.code.core.contatiner.PostProccessorContatiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);
    private MyContainer container;
    private PostProccessorContatiner postProccessorContatiner;
    private String basePackage;

    public void injectBeanFactoryAwaresBeans(){
        for (String name : container.getKeySet()) {
            Object bean = container.getByName(name);
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanName(name);
            }
        }
    }

    public abstract void setAllFieldsContext();

    public void init(String folderPath) {
        container = MyContainer.getInstance();
        postProccessorContatiner = PostProccessorContatiner.getInstance();
        basePackage = folderPath;
    }

    public void initializeBeans() {
        for (String name : container.getKeySet()) {
            Object bean = container.getByName(name);
            for (BeanPostProcessor postProcessor : postProccessorContatiner.getAll()) {
                postProcessor.postProcessBeforeInitialization(bean, name);
            }
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }
            for (BeanPostProcessor postProcessor : postProccessorContatiner.getAll()) {
                postProcessor.postProcessAfterInitialization(bean, name);
            }
        }
    }

    public void addToBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        postProccessorContatiner.addPostProcessor(beanPostProcessor);
    }

    public MyContainer getContainer() {
        return container;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public PostProccessorContatiner getPostProccessorContatiner() {
        return postProccessorContatiner;
    }
}
