package com.gcore.code.core.factory.beanfactory;

import com.gcore.code.core.exception.FolderNotFoundException;
import com.gcore.code.core.factory.BeanFactory;
import com.gcore.code.core.factory.BeanFactoryAware;
import com.gcore.code.core.metadata.inject.Autowired;
import com.gcore.code.core.metadata.inject.Inject;
import com.gcore.code.core.metadata.stereotype.Bean;
import com.gcore.code.core.metadata.stereotype.Component;
import com.gcore.code.core.metadata.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public final class BeanAnnotationFactory extends BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanAnnotationFactory.class);

    @Override
    public void init(String folderPath) {
        super.init(folderPath);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(folderPath);
            parseFilesDataFromFolders(resources);
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new FolderNotFoundException("Can't find this folder in your application");
        }
    }

    @Override
    public void setAllFieldsContext() {
        for (Object object : super.getContainer().getAllValues()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)
                        || field.isAnnotationPresent(Inject.class)) {
                    setFields(field, object);
                }
            }
        }
    }

    @Override
    public void injectBeanFactoryAwaresBeans() {
        for (String name : super.getContainer().getKeySet()) {
            Object bean = super.getContainer().getByName(name);
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanName(name);
            }
        }
    }

    private void setFields(Field field, Object object) {
        for (Object dependency : super.getContainer().getAllValues()) {
            if (dependency.getClass().equals(field.getType())) {
                String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                try {
                    Method setter = object.getClass().getMethod(setterName, dependency.getClass());
                    setter.invoke(object, dependency);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private void parseFilesDataFromFolders(Enumeration<URL> resources) {
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            try {
                File file = new File(resource.toURI());
                parseMetaDataFromFiles(file);
            } catch (URISyntaxException e) {
                logger.info(e.getMessage());
            }
        }
    }

    private void parseMetaDataFromFiles(File file) {
        for (File classFile : file.listFiles()) {
            String fileName = classFile.getName();
            logger.info("Package names: " + fileName);
            if (fileName.endsWith(".class")) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                try {
                    Class classObject = Class.forName(super.getBasePackage().replace("/", ".") + "." + className);
                    if (classObject.isAnnotationPresent(Bean.class)
                            || classObject.isAnnotationPresent(Service.class)
                            || classObject.isAnnotationPresent(Component.class)) {
                        logger.info("Bean in class " + classObject);
                        Object instance = classObject.newInstance();
                        String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                        super.getContainer().addToContainer(beanName, instance);
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
