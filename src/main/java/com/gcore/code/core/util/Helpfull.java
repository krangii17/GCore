package com.gcore.code.core.util;

public class Helpfull {
    private final String DEFAULT_FOLDER = "src/main/resources/gcoreconfig.properties";
    private final String FOLDER_PROPERTIES_KEY = "gcore.folder";

    public String getDefaultFolder() {
        return new EOLString(DEFAULT_FOLDER).toString();
    }

    public String getFolderPropertiesKey() {
        return new EOLString(FOLDER_PROPERTIES_KEY).toString();
    }

    private class EOLString {
        private final String origin;

        public EOLString(String origin) {
            this.origin = origin;
        }

        @Override
        public String toString() {
            return origin;
        }
    }
}
