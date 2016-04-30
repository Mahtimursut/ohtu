package referencelibrary;

import referencelibrary.reference.Reference;
import referencelibrary.data.ReferenceDao;
import referencelibrary.util.BibTeXConverter;
import referencelibrary.util.DuplicateNameException;
import referencelibrary.util.FileUtil;

import java.util.List;
import referencelibrary.util.NullNameException;

/**
 * Created by petri on 11/04/16.
 */
public class App {

    private ReferenceDao references;

    public App(ReferenceDao references) {
        this.references = references;
    }

    public void newReference(Reference reference) throws DuplicateNameException, NullNameException {
        references.add(reference);
    }

    public List<Reference> listReferences() {
        return references.listAll();
    }

    /**
     * Finds a reference with a given name
     *
     * @param referenceName Name of the reference to be looked for
     * @return Reference if found, null otherwise
     */
    public Reference find(String referenceName) {
        return references.find(referenceName);
    }
        
    public void generateBixTexFile(String filename) {
        //convert all references to BibTeX-format
        StringBuilder bibtexStringBuilder = new StringBuilder();
        for (Reference r : this.references.listAll()) {
            String refString = BibTeXConverter.convertToBibTeX(r);
            bibtexStringBuilder.append(refString);
        }
        //save String containing bibtex-references to File
        FileUtil.Write(filename, bibtexStringBuilder.toString());
    }

    /**
     * Removes references with given name
     *
     * @param referenceName
     */
    public void removeReference(String referenceName) {
        references.remove(referenceName);
    }

    /**
     * Stores changes made to any Reference
     */
    public void saveChanges() {
        references.saveChanges();
    }
}
