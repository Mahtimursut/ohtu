package referencelibrary.data;

import referencelibrary.reference.Reference;
import referencelibrary.util.DuplicateNameException;

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
     * Finds a reference with a given name
     * @return Reference if found, null otherwise
     */
    Reference find(String referenceName);

    /**
     * Adds a new Reference
     * @param reference Reference to be added
     * @throws referencelibrary.util.DuplicateNameException
     */
    void add(Reference reference) throws DuplicateNameException;

    /**
     * Removes a references of given name
     *
     * @param referenceName
     */
    void remove(String referenceName);

    /**
     * Stores changes made to any Reference
     */
    void saveChanges();
}
