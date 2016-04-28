package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.Reference;
import referencelibrary.util.FieldValidator;

import java.util.HashMap;
import java.util.Map;

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

        io.print("\nEditing reference " + reference + "\n");
        editFieldsOneByOne(reference);
        new AddOptionalFieldsSubCommand(io, reference, validator).execute();
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
        Map<String, String> fields = new HashMap<>(reference.getFieldValues());
        fields.forEach((k, v) -> editField(k, v, reference));
    }

    private void editField(String field, String oldValue, Reference reference) {
        StringBuilder options = new StringBuilder(field + ": " + oldValue + "\n" +
                "Press \t(enter) to keep [" + oldValue + "] (default)\n" +
                "\t\t(r)eplace value\n"
        );
        if (!reference.getRequiredFields().contains(field))
            options.append("\t\t(d)elete field\n");

        String command = io.readLine(options.toString());
        switch (command) {
            case "r":
                updateField(field, reference);
                break;
            case "d":
                removeField(field, reference);
                break;
            default:
        }
    }

    private void updateField(String field, Reference reference) {
        String value;
        do {
            value = io.readLine(field + ": ");
            if (value.isEmpty()) return;
        } while(!validator.fieldValueIsValid(value));
        reference.setField(field, value);
        io.print("Field " + field + " updated!\n");
    }

    private void removeField(String field, Reference reference) {
        if (reference.removeField(field))
            io.print("Field " + field + " removed!\n");
        else io.print("Failed to remove the field.\n");
    }
}
