package referencelibrary;

import org.junit.Before;
import org.junit.Test;
import referencelibrary.data.ReferenceDao;
import referencelibrary.data.StubDao;
import referencelibrary.reference.BookReference;
import referencelibrary.reference.Reference;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by petri on 18.4.2016.
 */
public class AppTest {
    private App app;

    @Before
    public void setUp() {
        app = new App(new StubDao());
    }

    @Test
    public void newReference() {
        app.newReference(new BookReference("REF1"));
        List<Reference> reflist = app.listReferences();
        assertEquals(2, reflist.size());
        assertEquals("REF1", reflist.get(1).getReferenceName());
    }

    @Test
    public void listReferences() {
        List<Reference> reflist = app.listReferences();
        assertEquals("REF", reflist.get(0).getReferenceName());
    }

//    @Test
    public void generateBixTexFile() {
        // TODO Test BibTex generation after adding support for dynamic BibTex file naming
    }

}