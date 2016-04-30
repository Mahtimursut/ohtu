package referencelibrary.data;

import referencelibrary.reference.Reference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
import referencelibrary.reference.BookReference;
import referencelibrary.util.DuplicateNameException;
import referencelibrary.util.NullNameException;

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
    public void correctNumberOfReferencesReturnedAfterAddition() throws DuplicateNameException, NullNameException {
        Reference reference2 = new BookReference();
        reference2.setReferenceName("uniqueName");
        refs.add(reference);
        refs.add(reference2);
        List<Reference> references = refs.listAll();
        assertEquals(2, references.size());
    }

    @Test
    public void correctReferenceReturnedAfterAddition() throws DuplicateNameException, NullNameException {
        refs.add(reference);
        List<Reference> references = refs.listAll();
        assertEquals("TEST", references.get(0).getReferenceName());
    }

    @Test
    public void referencesPersistInStorage() throws DuplicateNameException, NullNameException {
        refs.add(reference);
        FileReferenceDao newRefs = new FileReferenceDao("references_test");
        List<Reference> persistedRefs = newRefs.listAll();
        assertEquals("TEST", persistedRefs.get(0).getReferenceName());
    }

    @Test
    public void referenceWithDuplicateNameNotStored() throws NullNameException {
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

    @Test
    public void referenceWithNullNameNotStored() throws DuplicateNameException {
        Reference nullNameRef = new BookReference();
        nullNameRef.setReferenceName(null);
        try {
            refs.add(nullNameRef);
            fail("Exception was not thrown when adding a reference with null name.");
        } catch (NullNameException e) {
            assertEquals(0, refs.listAll().size());
        }
    }

    @Test(expected=DuplicateNameException.class)
    public void additionOfReferenceWithDuplicateNameThrowsException() throws DuplicateNameException, NullNameException {
        Reference duplicate = new BookReference();
        duplicate.setReferenceName("TEST");
        refs.add(reference);
        refs.add(duplicate);
    }

    @Test
    public void duplicateNameCheckShouldBeCaseInsensitive() throws NullNameException {
        Reference duplicate = new BookReference();
        duplicate.setReferenceName("test");
        try {
            refs.add(reference);
            refs.add(duplicate);
            fail("Exception was not thrown when adding a reference with a duplicate name" +
                    "but different case");
        } catch (DuplicateNameException e) {
            assertEquals(1, refs.listAll().size());
        }
    }

    @Test
    public void removeRemovesExistingReferenceOfGivenName() throws DuplicateNameException, NullNameException {
        final String removeMe = "removeMe";
        String[] referenceNames = {"test1", removeMe, "test2"};

        for (String referenceName : referenceNames) {
            Reference ref = new BookReference();
            ref.setReferenceName(referenceName);
            refs.add(ref);
        }

        refs.remove(removeMe);
        List<Reference> reflist = refs.listAll();
        for (Reference reference1 : reflist) {
            assertNotEquals(removeMe, reference1.getReferenceName());
        }
        assertEquals(2, reflist.size());
    }

    @Test
    public void removeDoesNothingWhenNonexistingReferenceNameGiven() throws DuplicateNameException, NullNameException {
        final String removeMe = "removeMe";

        refs.add(reference);

        refs.remove(removeMe);
        assertEquals(1, refs.listAll().size());
    }

    @Test
    public void correctReferenceIsFound() throws Exception {
        refs.add(reference);
        Reference found = refs.find("TEST");
        assertEquals(reference, found);
    }

    @Test
    public void searchingForNonexistingReferenceShouldReturnNull() throws Exception {
        Reference found = refs.find("no_such_ref");
        assertEquals(null, found);
    }

    @Test
    public void changesAreTemporaryBeforeSaving() throws Exception {
        Reference ref = new BookReference();
        ref.setReferenceName("ref");
        ref.setField("title", "oldie");
        refs.add(ref);
        ref.setField("title", "newbie");
        assertEquals("oldie", refs.find("ref").getField("title"));
    }

    @Test
    public void changesPersistWhenSaved() throws Exception {
        Reference ref = new BookReference();
        ref.setReferenceName("ref");
        ref.setField("title", "oldie");
        refs.add(ref);
        ref.setField("title", "newbie");
        refs.saveChanges();
        assertEquals("newbie", refs.find("ref").getField("title"));
    }
}