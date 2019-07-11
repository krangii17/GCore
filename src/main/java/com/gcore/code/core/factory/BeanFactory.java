package com.gcore.code.core.factory;

import com.gcore.code.core.contatiner.MyContainer;
import com.gcore.code.core.exception.FolderNotFoundException;
import com.gcore.code.core.metadata.stereotype.Component;
import com.gcore.code.core.metadata.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);
    private MyContainer container;
    private String basePackage;

    public void init(String folderPath) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(folderPath);
            parseFilesDataFromFolders(resources);
            basePackage = folderPath;
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new FolderNotFoundException("Can't find this folder in your application");
        }
    }

    private void parseFilesDataFromFolders(Enumeration<URL> resources){
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
                    Class classObject = Class.forName(basePackage + "." + className);
                    if (classObject.isAnnotationPresent(Component.class) || classObject.isAnnotationPresent(Service.class)) {
                        logger.info("Bean in class " + classObject);
                        Object instance = classObject.newInstance();
                        String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                        container.addToContainer(beanName, instance);
                    }
                }
                catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

}
