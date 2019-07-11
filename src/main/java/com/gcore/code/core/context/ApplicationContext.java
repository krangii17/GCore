package com.gcore.code.core.context;

import com.gcore.code.core.factory.BeanFactory;
import com.gcore.code.core.parser.PropertiesParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
    private BeanFactory beanFactory = new BeanFactory();
    private PropertiesParse parse;

    public ApplicationContext() {
        parse = new PropertiesParse();
        init();
    }

    public ApplicationContext(String scanPackage) {
        parse = new PropertiesParse(scanPackage);
        init();
    }

    private void init(){
        logger.info("_______ApplicationContext_______");
        beanFactory.init(parse.getFolder());
    }

}
