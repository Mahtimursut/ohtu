package referencelibrary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creted by petri on 11/04/16
 */
public class FileReferenceDao implements ReferenceDao {
    private File file;
    private List<Reference> references;

    public FileReferenceDao(String filename) {
        file = new File(filename);
        references = readReferences();
    }

    private List<Reference> readReferences() {
        try {
            return readFromStream();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

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

    private void writeReferenceListToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(references);
        oos.flush();
        oos.close();
        fos.close();
    }
}