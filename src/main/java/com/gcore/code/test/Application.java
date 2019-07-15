package com.gcore.code.test;

import com.gcore.code.core.context.ApplicationContext;
import com.gcore.code.core.context.xml.XMLApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new XMLApplicationContext("src/main/resources/beans.xml");
        Profile profile = (Profile) context.getBeanByName("profile");
        profile.user = new User();
    }
}
