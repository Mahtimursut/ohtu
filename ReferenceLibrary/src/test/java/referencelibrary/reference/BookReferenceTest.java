/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.reference;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rimi
 */
public class BookReferenceTest {

    BookReference reference = null;

    public BookReferenceTest() {
    }

    @Before
    public void setUp() {
        this.reference = new BookReference();
        this.reference.setReferenceName("my_book");
    }

    @Test
    public void testConstructor() {
        reference = new BookReference();
        reference.setReferenceName("my_book");
        assertEquals("my_book", reference.getReferenceName());
        assertEquals(ReferenceType.REFERENCE_BOOK, reference.getReferenceType());
        assertTrue(reference.getFieldValues().isEmpty());
    }

    @Test
    public void testSetReferenceName() {
        String name = "newname";
        reference.setReferenceName(name);
        assertEquals(name, reference.getReferenceName());
    }

    @Test
    public void testToString() {
        String expected = "[Book: my_book]";
        reference.setField("title", "Book of testing");
        reference.setField("note", "Contains various tests");
        assertEquals(expected, reference.toString());
    }

    @Test
    public void removeAuthorFromRequiredFields() {
        assertTrue(reference.getRequiredFields().contains("author"));
        assertFalse(reference.getRequiredFields().contains("editor"));
        reference.setEditorAsObligatory();
        assertFalse(reference.getRequiredFields().contains("author"));
        assertTrue(reference.getRequiredFields().contains("editor"));
    }

}
