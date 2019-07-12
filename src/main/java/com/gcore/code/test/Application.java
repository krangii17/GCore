package com.gcore.code.test;

import com.gcore.code.core.context.ApplicationContext;
import com.gcore.code.core.context.annotation.AnnotationApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationApplicationContext();
        context.addToBeanPostProcessorContainer(new BeanPostProccessorImpl());
        Profile profile = (Profile) context.getBeanByName("profile");
        profile.aVoid();
        context.initBeanPostProcessor();
    }
}
