package com.gcore.code.core.contatiner;

import com.gcore.code.core.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public class PostProccessorContatiner {
    private List<BeanPostProcessor> postProcessors = new ArrayList<>();

    public void addPostProcessor(BeanPostProcessor postProcessor) {
        postProcessors.add(postProcessor);
    }

    public List<BeanPostProcessor> getAll() {
        return postProcessors;
    }
}
