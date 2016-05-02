package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.Reference;
import referencelibrary.util.DuplicateNameException;
import referencelibrary.util.FieldValidator;
import referencelibrary.util.NullNameException;

/**
 * A general reference class addReferenceCommand
 *
 *
 * @author juhapekm
 */
abstract class AddReferenceCommand extends Command {

    private FieldValidator fieldValidator;

    AddReferenceCommand(App app, IO io) {
        super(app, io);
        fieldValidator = new FieldValidator(io);
    }

    private void setReferenceName(Reference reference) { 
        while(true) {
            String referenceName = io.readLine("Reference id");
            if (!fieldValidator.referenceNameIsUnique(referenceName, app.listReferences())) {
                io.print("There already exists a reference with the name " + referenceName
                        + "\nReference name must be unique.");
            }
            else if (!fieldValidator.referenceNameIsNotEmpty(referenceName)) {
                io.print("Reference name should not be empty.");
            }
            else {
                reference.setReferenceName(referenceName);
                break;
            }
        }
    }
    
    /**
     * Prompts for reference fields, checks for duplicate reference names and stores the reference.
     * @param reference The Reference to add
     */
    void addReference(Reference reference) {
        setReferenceName(reference);
        fillReferenceFields(reference);
        while (true) {
            try {
                app.newReference(reference);
                return;
            } catch (DuplicateNameException | NullNameException e) {
                String name = promptForNewReferenceName();
                reference.setReferenceName(name);
            }
        }
    }

    /**
     * Prompts for required fields and allows the user to add optional fields.
     *
     * @param reference The Reference to which to add fields
     */
    private void fillReferenceFields(Reference reference) {
        fillRequiredFields(reference);
        new AddOptionalFieldsSubCommand(io, reference, fieldValidator).execute();
    }

    /**
     * Prompts for the fields required in the particular reference type.
     * @param reference The Reference to which to add the fields
     */
    private void fillRequiredFields(Reference reference) {
        reference.getRequiredFields().getFields().forEach(field -> reference.setField(field, promptForField(field)));
    }

    /**
     * Asks the user for value for a given field name.
     *
     * @param fieldName Name of the field for which to ask the value
     * @return Value for the field
     */
    private String promptForField(String fieldName) {
        String fieldValue = "";
        do {
            fieldValue = io.readLine(fieldName + ":");
        } while (!fieldValidator.fieldValueIsValid(fieldValue));
        return fieldValue;
    }

    private String promptForNewReferenceName() {
        io.print("A reference with the given reference id already exists.\n" +
                "Please give another reference id for this reference:");
        return io.readLine("id:");
    }
}
