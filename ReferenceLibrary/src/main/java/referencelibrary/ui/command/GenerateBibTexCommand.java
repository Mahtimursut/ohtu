package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;

/**
 * Created by petri on 16.4.2016.
 */
public class GenerateBibTexCommand extends Command {

    public GenerateBibTexCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        io.print("\nGenerating bibTex -file");
        //call bibtex-file generator
        app.generateBixTexFile();

        io.print("Generating done!\n");
    }
}
