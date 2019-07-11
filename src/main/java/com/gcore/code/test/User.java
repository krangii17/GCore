package com.gcore.code.test;

import com.gcore.code.core.metadata.stereotype.Component;

@Component
public class User {
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
}
