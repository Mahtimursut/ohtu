package referencelibrary.data;

import referencelibrary.reference.Reference;

import java.util.List;

/**
 * Created by petri on 11/04/16.
 */
public interface ReferenceDao {

    /**
     * Lists all stored References
     * @return List of References
     */
    List<Reference> listAll();

    /**
     * Adds a new Reference
     * @param reference Reference to be added
     */
    void add(Reference reference);
}
