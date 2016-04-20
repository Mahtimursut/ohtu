/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.util;

import referencelibrary.reference.Reference;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import referencelibrary.reference.BookReference;
import referencelibrary.util.BibTeXConverter;

/**
 *
 * @author sjsarsa
 */
public class FieldValidatorTest {
    
    Reference reference;
    FieldValidator fieldValidator;
    public FieldValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        reference = new BookReference("a book");
        fieldValidator = new FieldValidator(reference);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFieldNameIsValid() {
        assertEquals(true, fieldValidator.fieldNameIsValid("author"));
        assertEquals(false, fieldValidator.fieldNameIsValid("kebab"));
        assertEquals(true, fieldValidator.fieldNameIsValid("series"));
    }
    
    @Test
    public void testFieldValueIsValid() {
        assertEquals(false, fieldValidator.fieldValueIsValid(""));
        assertEquals(true, fieldValidator.fieldValueIsValid("yay123"));
    }
}
