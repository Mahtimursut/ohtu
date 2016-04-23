package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.InproceedingsReference;
import referencelibrary.reference.Reference;
import referencelibrary.reference.ReferenceType;

/**
 *
 * @author juhapekm
 */
class AddInproceedingsCommand extends AddReferenceCommand{

    AddInproceedingsCommand(App app, IO io) {
        super(app, io);
    }
    
    @Override
    public void execute() {
        Reference reference = new InproceedingsReference();
        super.addReference(reference);
    }
}
