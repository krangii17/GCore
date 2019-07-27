package com.gcore.code.core.context.xml;

import com.gcore.code.core.config.BeanPostProcessor;
import com.gcore.code.core.context.ApplicationContext;
import com.gcore.code.core.factory.BeanFactory;
import com.gcore.code.core.factory.beanfactory.BeanXMLFactory;
import com.gcore.code.core.parser.PropertiesParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class XMLApplicationContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(XMLApplicationContext.class);
    private BeanFactory beanFactory = new BeanXMLFactory();
    private PropertiesParse parse;
    private String xmlDefenition;

    public XMLApplicationContext(String xmlDefenition) {
        this.xmlDefenition = xmlDefenition;
        parse = new PropertiesParse();
        init();
    }

    public XMLApplicationContext(String xmlDefenition, String scanPackage) {
        this.xmlDefenition = xmlDefenition;
        parse = new PropertiesParse(scanPackage);
        init();
    }

    private void init() {
        logger.info("_______XMLApplicationContext_______");
        beanFactory.init(xmlDefenition);
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
