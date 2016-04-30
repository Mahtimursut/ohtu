package referencelibrary.data;

import org.junit.Before;
import org.junit.Test;
import referencelibrary.reference.BookReference;
import referencelibrary.reference.Reference;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by petri on 18.4.2016.
 */
public class StubDaoTest {
    private StubDao refs;

    @Before
    public void setUp() {
        refs = new StubDao();
    }

    @Test
    public void listAll() {
        List<Reference> reflist = refs.listAll();
        assertEquals(1, reflist.size());
        assertEquals("REF", reflist.get(0).getReferenceName());
    }

    @Test
    public void add() {
        refs.add(new BookReference());
        refs.listAll().get(1).setReferenceName("REF1");
        List<Reference> reflist = refs.listAll();
        assertEquals("REF1", reflist.get(1).getReferenceName());
    }

    @Test
    public void removeRemovesReferences() {
        String referenceName = "remove_me";

        Reference ref = new BookReference();
        ref.setReferenceName(referenceName);
        refs.add(ref);

        assertTrue(refs.remove(referenceName));
        List<Reference> reflist = refs.listAll();
        assertEquals(1, reflist.size());
        assertNotEquals(referenceName, reflist.get(0).getReferenceName());
    }

    @Test
    public void find() throws Exception {
        Reference ref = new BookReference();
        ref.setReferenceName("find_me");
        refs.add(ref);
        Reference found = refs.find("find_me");
        assertEquals(ref, found);
    }

    @Test
    public void searchingForNonexistingReferenceShouldReturnNull() throws Exception {
        Reference ref = refs.find("no_such_ref");
        assertEquals(null, ref);
    }

    @Test
    public void saveChanges() throws Exception {
        Reference ref = refs.find("REF");
        ref.setField("title", "changed");
        refs.saveChanges();
        ref = refs.find("REF");
        assertEquals("changed", ref.getField("title"));
    }
}
