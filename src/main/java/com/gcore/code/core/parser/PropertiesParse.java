package com.gcore.code.core.parser;

import com.gcore.code.core.exception.PropertiesNotFoundException;
import com.gcore.code.core.util.Helpfull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParse {
    private String folder;

    public PropertiesParse(){
        this.folder = Helpfull.DEFAULT_FOLDER;
    }

    public PropertiesParse(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        Properties property = new Properties();
        try {
            InputStream fis = new FileInputStream(folder);
            property.load(fis);
            return replacer(property.getProperty(Helpfull.FOLDER_PROPERTIES_KEY));
        } catch (IOException e) {
            throw new PropertiesNotFoundException("Error to find properties file");
        }
    }

    private String replacer(String replaceDots) {
        return replaceDots.replace(".", "/");
    }
}
