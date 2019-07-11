package com.gcore.code.core.context;

import com.gcore.code.core.factory.BeanFactory;
import com.gcore.code.core.parser.PropertiesParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
    private BeanFactory beanFactory;

    public ApplicationContext(String scanPackage) {
        logger.info("_______ApplicationContext_______");
        beanFactory = new BeanFactory();
        PropertiesParse parse = new PropertiesParse();
        beanFactory.init(parse.getFolder());
    }

}
