package referencelibrary.reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author juhapekm
 */
public class InproceedingsReferenceTest {

    Reference reference = null;

    public InproceedingsReferenceTest() {
    }

    @Before
    public void setUp() {
        this.reference = new InproceedingsReference("my_inproceedings");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        reference = new InproceedingsReference("my_inproceedings");
        assertEquals("my_inproceedings", reference.getReferenceName());
        assertEquals(ReferenceType.REFERENCE_INPROCEEDINGS, reference.getReferenceType());
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
    public void testSetFieldOptional() {
        String fieldName = "note";
        String expectedValue = "great edition";
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));

        expectedValue = "great edition indeed";
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
        String expected = "[Inproceedings: my_inproceedings]";
        reference.setField("title", "Inproceedings of testing");
        reference.setField("note", "Contains various tests");
        assertEquals(expected, reference.toString());
    }

}
