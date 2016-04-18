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
public class AddInproceedingsCommand extends AddReferenceCommand{

    public AddInproceedingsCommand(App app, IO io) {
        super(app, io);
    }
    
    @Override
    public void execute() {
        //prompt reference name
        String referenceName = io.readLine("Reference id");

        Reference newRef = new InproceedingsReference(referenceName);
        super.fillReferenceFields(newRef);

        //save the reference
        app.newReference(newRef);
    }
}
