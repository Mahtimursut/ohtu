package referencelibrary.data;

import referencelibrary.reference.Reference;
import referencelibrary.util.DuplicateNameException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creted by petri on 11/04/16
 */
public class FileReferenceDao implements ReferenceDao {

    private final File file;
    private List<Reference> references;

    /**
     * Creates a FileReferenceDao that uses a file with a given name for storage
     *
     * @param filename filename for storing References
     */
    public FileReferenceDao(String filename) {
        file = new File(filename);
        references = readReferences();
    }

    /**
     * Reads stored References
     *
     * @return List of References
     */
    private List<Reference> readReferences() {
        try {
            return readFromStream();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Reads the Reference List from the file
     *
     * @return List of References
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private List<Reference> readFromStream() throws IOException, ClassNotFoundException {
        List<Reference> refs;
        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            refs = (List<Reference>) ois.readObject();
        }
        return refs;
    }

    @Override
    public List<Reference> listAll() {
        this.references = readReferences();
        return references;
    }

    @Override
    public Reference find(String referenceName) {
        this.references = readReferences();
        for (Reference r : this.references)
            if (r.getReferenceName().equalsIgnoreCase(referenceName))
                return r;

        return null;
    }

    @Override
    public void add(Reference reference) throws DuplicateNameException {
        if (references.contains(reference))
            throw new DuplicateNameException("References already contain a reference with the given name.");
        references.add(reference);

        try {
            writeReferenceListToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the Reference List to the file
     *
     * @throws IOException
     */
    private void writeReferenceListToFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(references);
            oos.flush();
        }
    }

    @Override
    public void remove(String referenceName) {
        if (references.removeIf(r -> r.getReferenceName().equals(referenceName))) {
            try {
                writeReferenceListToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveChanges() {
        try {
            writeReferenceListToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
