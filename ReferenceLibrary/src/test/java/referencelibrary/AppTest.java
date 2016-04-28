package referencelibrary;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import referencelibrary.data.StubDao;
import referencelibrary.reference.BookReference;
import referencelibrary.reference.Reference;

import java.util.List;
import org.junit.After;

import static org.junit.Assert.*;

import referencelibrary.util.DuplicateNameException;
import referencelibrary.util.FileUtil;

/**
 * Created by petri on 18.4.2016.
 */
public class AppTest {
    private final String filename = "test_bibtex.bib";
    private App app;

    @Before
    public void setUp() {
        app = new App(new StubDao());
        File testFile = new File(filename);
        testFile.delete();
    }

    @After
    public void tearDown() {
        File testFile = new File(filename);
        testFile.delete();
    }

    @Test
    public void newReference() throws DuplicateNameException {
        app.newReference(new BookReference());
        List<Reference> reflist = app.listReferences();
        reflist.get(1).setReferenceName("REF1");
        assertEquals(2, reflist.size());
        assertEquals("REF1", reflist.get(1).getReferenceName());
    }

    @Test
    public void listReferences() {
        List<Reference> reflist = app.listReferences();
        assertEquals("REF", reflist.get(0).getReferenceName());
    }

    @Test
    public void generateBixTexFile() {
        app.generateBixTexFile(filename);
        String result = FileUtil.Read(filename);
        assertEquals("@Book{REF,\n}\n\n", result);
    }

    @Test
    public void removeReferenceRemovesReferences() {
        app.removeReference("REF");
        assertTrue(app.listReferences().isEmpty());
    }

    @Test
    public void findReturnsCorrectReference() throws Exception {
        Reference ref = new BookReference();
        ref.setReferenceName("test");
        app.newReference(ref);
        assertEquals(ref, app.find(ref.getReferenceName()));
    }

    @Test
    public void saveStoresChangedReference() throws Exception {
        Reference ref = new BookReference();
        ref.setReferenceName("test");
        ref.setField("title", "oldie");
        app.newReference(ref);
        ref.setField("title", "newbie");
        app.saveChanges();
        assertEquals("newbie", app.find("test").getField("title"));
    }
}