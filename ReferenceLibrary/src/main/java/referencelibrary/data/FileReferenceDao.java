package referencelibrary.data;

import referencelibrary.reference.Reference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creted by petri on 11/04/16
 */
public class FileReferenceDao implements ReferenceDao {
    private File file;
    private List<Reference> references;

    /**
     * Creates a FileReferenceDao that uses a file with a given name for storage
     * @param filename filename for storing References
     */
    public FileReferenceDao(String filename) {
        file = new File(filename);
        references = readReferences();
    }

    /**
     * Reads stored References
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
     * @return List of References
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private List<Reference> readFromStream() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Reference> refs = (List<Reference>)ois.readObject();
        ois.close();
        fis.close();
        return refs;
    }

    @Override
    public List<Reference> listAll() {
        this.references = readReferences();
        return references;
    }

    @Override
    public void add(Reference reference) {
        references.add(reference);
        try {
            writeReferenceListToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the Reference List to the file
     * @throws IOException
     */
    private void writeReferenceListToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(references);
        oos.flush();
        oos.close();
        fos.close();
    }
}