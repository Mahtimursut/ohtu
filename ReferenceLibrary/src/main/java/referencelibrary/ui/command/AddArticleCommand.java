package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.ArticleReference;
import referencelibrary.reference.Reference;

/**
 *
 * @author juhapekm
 */
class AddArticleCommand extends AddReferenceCommand {

    AddArticleCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        String referenceName = io.readLine("Reference id");
        Reference reference = new ArticleReference(referenceName);
        super.addReference(reference);
    }
}
