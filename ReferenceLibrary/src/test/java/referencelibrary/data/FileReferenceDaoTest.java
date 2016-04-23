package referencelibrary.data;

import referencelibrary.reference.Reference;
import referencelibrary.data.FileReferenceDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import referencelibrary.reference.BookReference;
import referencelibrary.util.DuplicateNameException;

/**
 * Created by petri on 11/04/16.
 */
public class FileReferenceDaoTest {
    private FileReferenceDao refs;
    private Reference reference;

    @Before
    public void setUp() throws Exception {
        refs = new FileReferenceDao("references_test");
        reference = new BookReference();
        reference.setReferenceName("TEST");
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
    public void correctNumberOfReferencesReturnedAfterAddition() throws DuplicateNameException {
        Reference reference2 = new BookReference();
        refs.add(reference);
        refs.add(reference2);
        List<Reference> references = refs.listAll();
        assertEquals(2, references.size());
    }

    @Test
    public void correctReferenceReturnedAfterAddition() throws DuplicateNameException {
        refs.add(reference);
        List<Reference> references = refs.listAll();
        assertEquals("TEST", references.get(0).getReferenceName());
    }

    @Test
    public void referencesPersistInStorage() throws DuplicateNameException {
        refs.add(reference);
        FileReferenceDao newRefs = new FileReferenceDao("references_test");
        List<Reference> persistedRefs = newRefs.listAll();
        assertEquals("TEST", persistedRefs.get(0).getReferenceName());
    }

    @Test
    public void referenceWithDuplicateNameNotStored() {
        Reference duplicate = new BookReference();
        duplicate.setReferenceName("TEST");
        try {
            refs.add(reference);
            refs.add(duplicate);
            fail("Exception was not thrown when adding a duplicate reference.");
        } catch (DuplicateNameException e) {
            assertEquals(1, refs.listAll().size());
        }
    }

    @Test(expected=DuplicateNameException.class)
    public void additionOfReferenceWithDuplicateNameThrowsException() throws DuplicateNameException {
        Reference duplicate = new BookReference();
        duplicate.setReferenceName("TEST");
        refs.add(reference);
        refs.add(duplicate);
    }
}