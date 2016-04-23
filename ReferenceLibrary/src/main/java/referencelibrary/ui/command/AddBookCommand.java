package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.BookReference;
import referencelibrary.reference.Reference;

/**
 * Created by petri on 16.4.2016.
 */
class AddBookCommand extends AddReferenceCommand {

    AddBookCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        BookReference reference = new BookReference();
        editorOrAuthorAsObligatoryField(reference);
        super.addReference(reference);
    }
    
    private void editorOrAuthorAsObligatoryField(BookReference reference) {String command = io.readLine(
            "\nDo you want author or editor as obligatory field?" +
                "\n(a)uthor (default)" +
                "\n(e)ditor\n\n");
        //switch (reference type)
        switch(command){
            case "a":
                break;
            case "e":reference.setEditorAsObligatory();
                break;
            default:
                io.print("author selected as default");
                break;
        } 
    }
}