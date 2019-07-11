package com.gcore.code.core.contatiner;

import java.util.HashMap;
import java.util.Map;

public class MyContainer {
    private Map<String, Object> map = new HashMap();

    public void addToContainer(String name, Object obj) {
        map.put(name, obj);
    }

    public Object getBean(String name) {
        return map.get(name);
    }

    public Map<String, Object> getAllBeans() {
        return map;
    }
}
