/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sjsarsa
 */
public class BibTeXConverterTest {
    
    Reference reference;
    
    public BibTeXConverterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        reference = new Reference("Book", "a book");
        reference.setField("author", "human");
        reference.setField("longFieldName", "Yay!");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConversion() {
        String bibtex = "@Book{a book,\n"
                + " author        = {human},\n"
                + " longFieldName = {Yay!},\n"
                + "}";
        assertEquals(bibtex, BibTeXConverter.convertToBibTeX(reference));
        reference.setField("something", "something");
        bibtex = "@Book{a book,\n"
                + " author        = {human},\n"
                + " longFieldName = {Yay!},\n"
                + " something     = {something},\n"
                + "}";
        assertEquals(bibtex, BibTeXConverter.convertToBibTeX(reference));
    }
}
