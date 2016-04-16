package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.IO;

/**
 * Created by petri on 16.4.2016.
 */
public abstract class Command {
    protected App app;
    protected IO io;

    public Command(App app, IO io) {
        this.app = app;
        this.io = io;
    }

    public abstract void execute();
}
