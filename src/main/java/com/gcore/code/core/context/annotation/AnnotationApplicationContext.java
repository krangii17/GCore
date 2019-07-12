package com.gcore.code.core.context.annotation;

import com.gcore.code.core.config.BeanPostProcessor;
import com.gcore.code.core.context.ApplicationContext;
import com.gcore.code.core.factory.BeanFactory;
import com.gcore.code.core.factory.beanfactory.BeanAnnotationFactory;
import com.gcore.code.core.parser.PropertiesParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationApplicationContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationApplicationContext.class);
    private BeanFactory beanFactory = new BeanAnnotationFactory();
    private PropertiesParse parse;

    public AnnotationApplicationContext() {
        parse = new PropertiesParse();
        init();
    }

    public AnnotationApplicationContext(String scanPackage) {
        parse = new PropertiesParse(scanPackage);
        init();
    }

    private void init() {
        logger.info("_______ApplicationContext_______");
        beanFactory.init(parse.getFolder());
        beanFactory.setAllFieldsContext();
        beanFactory.injectBeanFactoryAwaresBeans();
    }

    @Override
    public void initBeanPostProcessor() {
        beanFactory.initializeBeans();
    }

    @Override
    public void addToBeanPostProcessorContainer(BeanPostProcessor postProcessor) {
        beanFactory.addToBeanPostProcessor(postProcessor);
    }

    @Override
    public Object getBeanByName(String name) {
        return beanFactory.getContainer().getByName(name);
    }
}
