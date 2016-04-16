package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.IO;

/**
 * Created by petri on 16.4.2016.
 */
public class HelpCommand extends Command {

    public HelpCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        io.print("(q)uit," + "\n" +
                "(a)dd new reference," + "\n" +
                "(g)enerate bibtex" + "\n" +
                "(s)how references" + "\n");
    }
}
