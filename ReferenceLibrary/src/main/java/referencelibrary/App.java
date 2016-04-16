package referencelibrary;

import referencelibrary.reference.Reference;
import referencelibrary.data.ReferenceDao;

import java.util.List;

/**
 * Created by petri on 11/04/16.
 */
public class App {

    private ReferenceDao references;

    public App(ReferenceDao references) {
        this.references = references;
    }

    public void newReference(Reference reference) {
        references.add(reference);
    }

    public List<Reference> listReferences() {
        return references.listAll();
    }
        
    public void generateBixTexFile() {
        //convert all references to BibTeX-format
        StringBuilder bibtexStringBuilder = new StringBuilder();
        for (Reference r : this.references.listAll()) {
            String refString = BibTeXConverter.convertToBibTeX(r);
            bibtexStringBuilder.append(refString);
        }
        //save String containing bibtex-references to File
        FileWriter.Write("output.bst", bibtexStringBuilder.toString());
    }
}
