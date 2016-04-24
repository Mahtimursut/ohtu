package referencelibrary.reference;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author juhapekm
 */
public class ArticleReferenceTest {

    Reference reference = null;

    public ArticleReferenceTest() {
    }

    @Before
    public void setUp() {
        this.reference = new ArticleReference();
        this.reference.setReferenceName("my_article");
    }

    @Test
    public void testConstructor() {
        reference = new ArticleReference();
        reference.setReferenceName("my_article");
        assertEquals("my_article", reference.getReferenceName());
        assertEquals(ReferenceType.REFERENCE_ARTICLE, reference.getReferenceType());
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
        String expected = "[Article: my_article]";
        reference.setField("title", "Article of testing");
        reference.setField("note", "Contains various tests");
        assertEquals(expected, reference.toString());
    }

}
