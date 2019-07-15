package com.gcore.code.core.factory.beanfactory;

import com.gcore.code.core.config.BeanPostProcessor;
import com.gcore.code.core.contatiner.MyContainer;
import com.gcore.code.core.contatiner.PostProccessorContatiner;
import com.gcore.code.core.factory.BeanFactory;
import com.gcore.code.core.xmlprocessor.StaxStreamProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BeanXMLFactory implements BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanXMLFactory.class);
    private MyContainer container;
    private PostProccessorContatiner postProccessorContatiner;
    private String basePackage;

    @Override
    public void init(String folderPath) {
        container = MyContainer.getInstance();
        postProccessorContatiner = PostProccessorContatiner.getInstance();
        basePackage = folderPath;
    }

    @Override
    public void injectBeanFactoryAwaresBeans() {

    }

    @Override
    public void setAllFieldsContext() {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get(basePackage)))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT &&
                        "bean".equals(reader.getLocalName())) {
                    String clazzName = reader.getElementText().trim();
                    String realName = clazzName.substring((clazzName.lastIndexOf(".") + 1), clazzName.length()).toLowerCase();
                    Class clazz = Class.forName(clazzName);
                    Object instance = clazz.newInstance();
                    container.addToContainer(realName, instance);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void initializeBeans() {

    }

    @Override
    public void addToBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

    }

    @Override
    public MyContainer getContainer() {
        return container;
    }
}
