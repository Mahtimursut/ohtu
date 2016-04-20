package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.ArticleReference;
import referencelibrary.reference.Reference;

/**
 *
 * @author juhapekm
 */
public class AddArticleCommand extends AddReferenceCommand {

    public AddArticleCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        //prompt reference name
        String referenceName = io.readLine("Reference id");

        Reference newRef = new ArticleReference(referenceName);
        super.fillReferenceFields(newRef);

        //save the reference
        app.newReference(newRef);
    }
}
