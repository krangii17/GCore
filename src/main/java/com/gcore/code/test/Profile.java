package com.gcore.code;

import com.gcore.code.core.metadata.inject.Autowired;
import com.gcore.code.core.metadata.stereotype.Component;

@Component
public class Profile {
    @Autowired
    User user;

    public void aVoid(){
        System.out.println(user.getAge());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
