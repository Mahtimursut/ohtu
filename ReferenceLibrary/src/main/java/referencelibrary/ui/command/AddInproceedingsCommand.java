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
        String referenceName = io.readLine("Reference id");
        Reference reference = new InproceedingsReference(referenceName);
        super.addReference(reference);
    }
}
