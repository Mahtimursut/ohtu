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
        reference = new BookReference();
        reference.setReferenceName("a book");
        reference.setField("author", "human");
        reference.setField("title", "Yay!");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConversion() {
        String bibtex = "@Book{a book,\n"
                + " author = {human},\n"
                + " title  = {Yay!},\n"
                + "}\n\n";
        assertEquals(bibtex, BibTeXConverter.convertToBibTeX(reference));
        reference.setField("note", "something");
        bibtex = "@Book{a book,\n"
                + " author = {human},\n"
                + " note   = {something},\n"
                + " title  = {Yay!},\n"
                + "}\n\n";
        assertEquals(bibtex, BibTeXConverter.convertToBibTeX(reference));
    }
    
    @Test
    public void testGetLongestKeyLength() {
        assertEquals(BibTeXConverter.getLongestKeyLength(reference), 6);
        reference.setField("address", "möyh");
        assertEquals(BibTeXConverter.getLongestKeyLength(reference), 7);
    }
    
    @Test
    public void hiivatinLineCoverage() {
        Object o = new BibTeXConverter();
    }
}
