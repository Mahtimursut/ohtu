package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.IO;

/**
 * Created by petri on 16.4.2016.
 */
public class InvalidCommand extends Command {

    public InvalidCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        io.print("No such command.");
    }
}
