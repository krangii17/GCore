package com.gcore.code.core.contatiner;

import com.gcore.code.core.contatiner.exception.NoSuchBeanException;

import java.util.*;

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

    public Collection<Object> getAllValues(){
        return map.values();
    }

    public Object getByName(String name){
        if(map.get(name)!= null)
        {
            return map.get(name);
        }
        throw new NoSuchBeanException("No such bean with this name in container");
    }

    public Set<String> getKeySet(){
        return map.keySet();
    }
}
