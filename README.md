[![Build Status](https://travis-ci.org/krangii17/GCore.svg?branch=master)](https://travis-ci.org/krangii17/GCore)

This is Spring Core simple clone.

This is annotation way to use Gcore 
```java
package com.gcore.code.test;

import com.gcore.code.core.metadata.inject.Autowired;
import com.gcore.code.core.metadata.stereotype.Component;

@Component
public class Profile {
    @Autowired
    User user;

    public void aVoid() {
        System.out.println(user.getAge());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
```
`Application.class` application context class
```java
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
```
