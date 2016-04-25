package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;

/**
 * Created by petri on 16.4.2016.
 */
public class ShowReferencesCommand extends Command {

    public ShowReferencesCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        io.print("[Ref. id] Author: Title");
        io.print("-------------");
        app.listReferences().forEach(reference -> io.print(reference.getNameAndAuthorOrEditorAndTitle()));
    }
}
