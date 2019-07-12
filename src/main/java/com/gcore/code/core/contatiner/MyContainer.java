package com.gcore.code.core.contatiner;

import com.gcore.code.core.contatiner.exception.NoSuchBeanException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyContainer {
    private static Map<String, Object> map;

    private static MyContainer instance;

    private MyContainer(){ }

    public static synchronized MyContainer getInstance() {
        if (instance == null) {
            map = new HashMap();
            instance = new MyContainer();
        }
        return instance;
    }

    public void addToContainer(String name, Object obj) {
        map.put(name, obj);
    }

    public Object getBean(String name) {
        return map.get(name);
    }

    public Map<String, Object> getAllBeans() {
        return map;
    }

    public Collection<Object> getAllValues() {
        return map.values();
    }

    public Object getByName(String name) {
        if (map.get(name) != null) {
            return map.get(name);
        }
        throw new NoSuchBeanException("No such bean with this name in container");
    }

    public Set<String> getKeySet() {
        return map.keySet();
    }
}
