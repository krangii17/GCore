package com.gcore.code.core.contatiner;

import com.gcore.code.core.contatiner.exception.NoSuchBeanException;
import com.gcore.code.core.metadata.stereotype.Service;

import java.util.Collection;
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

    public Collection<Object> getAllValues(){
        return map.values();
    }

    public Object getByValue(String value){
        if(map.get(value)!= null)
        {
            return map.get(value);
        }
        throw new NoSuchBeanException("No such bean with this name in container");
    }
}
