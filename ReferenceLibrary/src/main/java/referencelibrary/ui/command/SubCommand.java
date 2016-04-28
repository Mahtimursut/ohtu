package referencelibrary.ui.command;

import referencelibrary.io.IO;
import referencelibrary.reference.Reference;
import referencelibrary.util.FieldValidator;

/**
 * Created by petri on 28.4.2016.
 */
abstract class SubCommand {
    protected IO io;
    protected Reference reference;
    protected FieldValidator validator;

    SubCommand(IO io, Reference reference, FieldValidator validator) {
        this.io = io;
        this.reference = reference;
        this.validator = validator;
    }

    public abstract void execute();
}
