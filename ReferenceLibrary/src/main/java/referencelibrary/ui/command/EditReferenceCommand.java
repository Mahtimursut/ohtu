package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.Reference;
import referencelibrary.util.FieldValidator;

/**
 * Created by petri on 28.4.2016.
 */
public class EditReferenceCommand extends Command {

    private FieldValidator validator;

    public EditReferenceCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        String name = io.readLine("Enter id of the reference:");
        Reference reference = findReference(name);

        if (reference == null) {
            io.print("Reference named " + name + " was not found.");
            return;
        }

        io.print("For each field, enter a new value or press enter to keep the original.");
        editFieldsOneByOne(reference);
        app.saveChanges();
        io.print("Changes to " + name + " saved!");
    }

    /**
     * Find a reference with a given name
     * @param referenceName Name of the reference to be looked for
     * @return Reference if found, null otherwise
     */
    private Reference findReference(String referenceName) {
        return app.find(referenceName);
    }

    private void editFieldsOneByOne(Reference reference) {
        validator = new FieldValidator(reference);

        reference.getFieldValues().forEach((k, v) -> {
            String value;
            do {
                value = promptForField(k, v);
                if (value.isEmpty()) return;
            } while(!validator.fieldValueIsValid(value));
            reference.setField(k, value);
        });
    }

    private String promptForField(String field, String oldValue) {
        return io.readLine(field + ": " + oldValue);
    }
}
