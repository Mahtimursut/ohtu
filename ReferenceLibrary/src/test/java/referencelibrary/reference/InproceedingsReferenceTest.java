package referencelibrary.reference;

import static org.junit.Assert.*;
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
        this.reference = new InproceedingsReference();
        this.reference.setReferenceName("my_inproceedings");
    }

    @Test
    public void testConstructor() {
        reference = new InproceedingsReference();
        reference.setReferenceName("my_inproceedings");
        assertEquals("my_inproceedings", reference.getReferenceName());
        assertEquals(ReferenceType.REFERENCE_INPROCEEDINGS, reference.getReferenceType());
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
        String expected = "[Inproceedings: my_inproceedings]";
        reference.setField("title", "Inproceedings of testing");
        reference.setField("note", "Contains various tests");
        assertEquals(expected, reference.toString());
    }

}
