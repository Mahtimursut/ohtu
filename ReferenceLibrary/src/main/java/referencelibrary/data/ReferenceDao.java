package referencelibrary.data;

import referencelibrary.reference.Reference;
import referencelibrary.util.DuplicateNameException;

import java.util.List;
import referencelibrary.util.NullNameException;

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
     * @throws referencelibrary.util.NullNameException
     */
    void add(Reference reference) throws DuplicateNameException, NullNameException;

    /**
     * Removes references of given name.
     * Returns true if something was removed.
     *
     * @param referenceName
     * @return removed
     */
    boolean remove(String referenceName);

    /**
     * Stores changes made to any Reference
     */
    void saveChanges();
}
