package referencelibrary;

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

    protected void newReference(Reference reference) {
        references.add(reference);
    }

    protected List<Reference> listReferences() {
        return references.listAll();
    }
}
