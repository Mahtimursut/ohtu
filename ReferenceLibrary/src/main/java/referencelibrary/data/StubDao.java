package referencelibrary.data;

import referencelibrary.reference.Reference;

import java.util.ArrayList;
import java.util.List;
import referencelibrary.reference.BookReference;

/**
 * Created by petri on 11/04/16.
 */
public class StubDao implements ReferenceDao {

    private List<Reference> refs;

    public StubDao() {
        refs = new ArrayList<>();
        Reference ref = new BookReference();
        ref.setReferenceName("REF");
        refs.add(ref);
    }

    @Override
    public List<Reference> listAll() {
        return refs;
    }

    @Override
    public Reference find(String referenceName) {
        for (Reference r : refs)
            if (r.hasReferenceName(referenceName))
                return r;

        return null;
    }

    @Override
    public void add(Reference reference) {
        refs.add(reference);
    }

    @Override
    public boolean remove(String referenceName) {
        return refs.removeIf(r -> r.getReferenceName().equals(referenceName));
    }

    @Override
    public void saveChanges() {
        // In-memory stub: no need to save
    }
}
