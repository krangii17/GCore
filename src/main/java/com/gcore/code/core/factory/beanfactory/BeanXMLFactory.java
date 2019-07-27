package com.gcore.code.core.factory.beanfactory;

import com.gcore.code.core.factory.BeanFactory;
import com.gcore.code.core.xmlprocessor.StaxStreamProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class BeanXMLFactory extends BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanXMLFactory.class);

    @Override
    public void setAllFieldsContext() {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get(super.getBasePackage())))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT &&
                        "bean".equals(reader.getLocalName())) {
                    String clazzName = reader.getElementText().trim();
                    String realName = clazzName.substring((clazzName.lastIndexOf(".") + 1), clazzName.length()).toLowerCase();
                    Class clazz = Class.forName(clazzName);
                    Object instance = clazz.newInstance();
                    super.getContainer().addToContainer(realName, instance);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
