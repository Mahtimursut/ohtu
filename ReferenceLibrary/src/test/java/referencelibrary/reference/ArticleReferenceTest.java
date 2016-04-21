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
public class ArticleReferenceTest {

    Reference reference = null;

    public ArticleReferenceTest() {
    }

    @Before
    public void setUp() {
        this.reference = new ArticleReference("my_article");
    }

    @Test
    public void testConstructor() {
        reference = new ArticleReference("my_article");
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
