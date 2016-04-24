package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;

/**
 * Created by petri on 16.4.2016.
 */
public class GenerateBibTexCommand extends Command {

    private static final String BIBTEX_FILE_ENDING = ".bib";

    public GenerateBibTexCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        io.print("\nGenerating bibTex -file");
        String filename = io.readLine("Please insert filename:");
        app.generateBixTexFile(filename+BIBTEX_FILE_ENDING);

        io.print("Generating done!\n");
    }
}
