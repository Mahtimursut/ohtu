/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import referencelibrary.reference.Reference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import referencelibrary.reference.BookReference;
import referencelibrary.reference.ReferenceType;

/**
 *
 * @author rimi
 */
public class BookReferenceTest {

    Reference reference = null;

    public BookReferenceTest() {
    }

    @Before
    public void setUp() {
        this.reference = new BookReference("my_book");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        reference = new BookReference("my_book");
        assertEquals("my_book", reference.getReferenceName());
        assertEquals(ReferenceType.REFERENCE_BOOK, reference.getReferenceType());
        assertTrue(reference.getFieldValues().isEmpty());
    }

    @Test
    public void testSetField() {
        String fieldName = "author";
        String expectedValue = "Risto Mikkola";
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));

        expectedValue = "Jukka-Pekka Moilanen";
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));
    }

    @Test
    public void testGetField() {
        String fieldName = "author";
        String expectedValue = "Sami Sarsa";
        assertNull(reference.getField(fieldName));
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));
    }

    @Test
    public void testSetReferenceName() {
        String name = "newname";
        reference.setReferenceName(name);
        assertEquals(name, reference.getReferenceName());
    }

    @Test
    public void testGetAllFields() {
        List<String> expectedFields = new ArrayList<>();
        expectedFields.addAll(reference.getRequiredFields());
        expectedFields.addAll(reference.getOptionalFields());
        List<String> fields = reference.getAllFields();

        Collections.sort(expectedFields);
        Collections.sort(fields);
        assertEquals(expectedFields, fields);
    }

    @Test
    public void testToString() {
        String expected = "[Book: my_book]";
        reference.setField("title", "Book of testing");
        reference.setField("note", "Contains various tests");
        assertEquals(expected, reference.toString());
    }

}
