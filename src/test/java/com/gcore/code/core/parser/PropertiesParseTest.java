package com.gcore.code.core.parser;

import com.gcore.code.core.exception.PropertiesNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class PropertiesParseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PropertiesParse parse;
    private PropertiesParse parseWithProperties;
    private PropertiesParse falseProperties;

    @Before
    public void setUp() throws Exception {
        parse = new PropertiesParse();
        parseWithProperties = new PropertiesParse("src/test/resources/gcore.properties");
        falseProperties = new PropertiesParse("this is false prop");
    }

    @After
    public void tearDown() throws Exception {
        parse = null;
        parseWithProperties = null;
        falseProperties = null;
    }

    @Test
    public void getFolderWithoutArguments() {
        //WHEN
        String folder = parse.getFolder();
        //THEN
        assertEquals("com/gcore/code/test", folder);
    }

    @Test
    public void getFolderWithArguments() {
        //WHEN
        String folder = parseWithProperties.getFolder();
        //THEN
        assertEquals("com/gcore/code/withargs", folder);
    }

    @Test()
    public void cantFindProperties() {
        thrown.expect(PropertiesNotFoundException.class);
        thrown.expectMessage("Error to find properties file");
        falseProperties.getFolder();
    }
}