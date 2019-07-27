package com.gcore.code.core.contatiner;

import com.gcore.code.core.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public final class PostProccessorContatiner {
    private static List<BeanPostProcessor> postProcessors;
    private static PostProccessorContatiner instance;

    private PostProccessorContatiner() {
    }

    public static synchronized PostProccessorContatiner getInstance() {
        if (instance == null) {
            postProcessors = new ArrayList<>();
            instance = new PostProccessorContatiner();
        }
        return instance;
    }

    public void addPostProcessor(BeanPostProcessor postProcessor) {
        postProcessors.add(postProcessor);
    }

    public List<BeanPostProcessor> getAll() {
        return postProcessors;
    }
}
