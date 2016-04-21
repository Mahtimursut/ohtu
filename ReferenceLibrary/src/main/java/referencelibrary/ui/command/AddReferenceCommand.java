package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.reference.Reference;
import referencelibrary.util.DuplicateNameException;
import referencelibrary.util.FieldValidator;

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
    }

    /**
     * Prompts for reference fields, checks for duplicate reference names and stores the reference.
     * @param reference The Referce to add
     */
    void addReference(Reference reference) {
        fillReferenceFields(reference);
        while (true) {
            try {
                app.newReference(reference);
                return;
            } catch (DuplicateNameException e) {
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
        this.fieldValidator = new FieldValidator(reference);
        fillRequiredFields(reference);

        // TODO maybe a yes/no UI function should be generalized
        String optionalAnswer = io.readLine("Would you like to add some optional fields?\n"
                + "\t(y)es\n"
                + "\t(n)o (default)\n");
        if (optionalAnswer.equalsIgnoreCase("y")) {
            fillOptionalFields(reference);
        }
    }

    /**
     * Prompts for the fields required in the particular reference type.
     * @param reference The Reference to which to add the fields
     */
    private void fillRequiredFields(Reference reference) {
        reference.getRequiredFields().forEach(field -> reference.setField(field, promptForField(field)));
    }

    /**
     * Allows the user to add optional fields to a reference.
     *
     * @param reference The Reference to which to add optional fields
     */
    private void fillOptionalFields(Reference reference) {
        while (true) {
            String command = io.readLine("How do you want to proceed?\n"
                    + "\t(s)how valid field names for this reference type\n"
                    + "\t(a)dd a field\n"
                    + "\t(d)one with adding fields\n");

            switch (command) {
                case "s":
                    showValidOptionalFields(reference);
                    break;
                case "a":
                    addOptionalFieldToReference(reference);
                    break;
                case "d":
                default:
                    return;
            }
        }
    }

    /**
     * Shows the valid optional fields.
     *
     * @param reference The Reference for which to show the valid fields
     */
    private void showValidOptionalFields(Reference reference) {
        reference.getOptionalFields().forEach(io::print);
    }

    /**
     * Adds a single field to a reference
     *
     * @param reference The Reference to which to add the field
     */
    private void addOptionalFieldToReference(Reference reference) {
        String fieldName;
        String fieldValue;
        // TODO should have some "backend" validation also (e.g. exception from Reference)
        do {
            fieldName = io.readLine("Field:");
        } while (!fieldValidator.fieldNameIsValid(fieldName));
        
        do {
            fieldValue = io.readLine(fieldName + ":");
        } while (!fieldValidator.fieldValueIsValid(fieldValue));
        reference.setField(fieldName, fieldValue);
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
