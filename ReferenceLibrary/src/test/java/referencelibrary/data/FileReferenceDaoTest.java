package referencelibrary.data;

import referencelibrary.reference.Reference;
import referencelibrary.data.FileReferenceDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
import referencelibrary.reference.BookReference;

/**
 * Created by petri on 11/04/16.
 */
public class FileReferenceDaoTest {
    private FileReferenceDao refs;

    @Before
    public void setUp() throws Exception {
        refs = new FileReferenceDao("references_test");
    }

    @After
    public void tearDown() {
        File testFile = new File("references_test");
        testFile.delete();
    }

    @Test
    public void emptyReferenceListReturnedWhenNoAdditions() {
        List<Reference> references = refs.listAll();
        assertTrue(references.isEmpty());
    }

    @Test
    public void correctNumberOfReferencesReturnedAfterAddition() {
        Reference reference = new BookReference("TEST");
        Reference reference2 = new BookReference("TEST2");
        refs.add(reference);
        refs.add(reference2);
        List<Reference> references = refs.listAll();
        assertEquals(2, references.size());
    }

    @Test
    public void correctReferenceReturnedAfterAddition() {
        Reference reference = new BookReference("TEST");
        refs.add(reference);
        List<Reference> references = refs.listAll();
        assertEquals("TEST", references.get(0).getReferenceName());
    }

    @Test
    public void referencesPersistInStorage() {
        Reference reference = new BookReference("TEST");
        refs.add(reference);
        FileReferenceDao newRefs = new FileReferenceDao("references_test");
        List<Reference> persistedRefs = newRefs.listAll();
        assertEquals("TEST", persistedRefs.get(0).getReferenceName());
    }
}