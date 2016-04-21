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

    @Test
    public void generateBixTexFile() {
        app.generateBixTexFile(filename);
        String result = FileUtil.Read(filename);
        assertEquals("@Book{REF,\n}\n\n", result);
    }

}