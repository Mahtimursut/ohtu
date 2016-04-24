package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;

/**
 * @author rimi
 */
public class RemoveReferenceCommand extends Command {

    public RemoveReferenceCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        io.print("Removing a reference with given id.");
        io.print("Give nothing to cancel.");
        String readLine = io.readLine("Reference id: ");
        if (!readLine.isEmpty()) {
            app.removeReference(readLine);
            io.print("References with given id removed!");
        } else {
            io.print("operation cancelled.");
        }
    }
}
