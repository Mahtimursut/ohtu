package referencelibrary.data;

import referencelibrary.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petri on 11/04/16.
 */
public class StubDao implements ReferenceDao {

    private List<Reference> refs;

    public StubDao() {
        refs = new ArrayList<>();
        Reference ref = new Reference("book", "REF");
        refs.add(ref);
    }

    @Override
    public List<Reference> listAll() {
        return refs;
    }

    @Override
    public void add(Reference reference) {
        refs.add(reference);
    }
}
