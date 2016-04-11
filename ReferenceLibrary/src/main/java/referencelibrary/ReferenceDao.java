package referencelibrary;

import java.util.List;

/**
 * Created by petri on 11/04/16.
 */
public interface ReferenceDao {
    List<Reference> listAll();
    void add(Reference reference);
}
