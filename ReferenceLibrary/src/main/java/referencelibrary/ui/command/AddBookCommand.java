package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.IO;
import referencelibrary.reference.BookReference;
import referencelibrary.reference.Reference;

/**
 * Created by petri on 16.4.2016.
 */
public class AddBookCommand extends Command {

    public AddBookCommand(App app, IO io) {
        super(app, io);
    }

    @Override
    public void execute() {
        //prompt reference name
        String referenceName = io.readLine("Reference id");

        Reference newRef = new BookReference(referenceName);
        fillReferenceFields(newRef);

        //save the reference
        app.newReference(newRef);
    }

    /**
     * Prompts for required fields and allows the user to add optional fields.
     * @param reference The Reference to which to add fields
     */
    private void fillReferenceFields(Reference reference) {
        fillRequiredFields(reference);

        // TODO maybe a yes/no UI function should be generalized
        String optionalAnswer = io.readLine("Would you like to add some optional fields?\n" +
                "\t(y)es\n" +
                "\t(n)o (default)\n");
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
     * @param reference The Reference to which to add optional fields
     */
    private void fillOptionalFields(Reference reference) {
        while (true) {
            String command = io.readLine("How do you want to proceed?\n" +
                    "\t(s)how valid field names for this reference type\n" +
                    "\t(a)dd a field\n" +
                    "\t(d)one with adding fields\n");

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
     * @param reference The Reference for which to show the valid fields
     */
    private void showValidOptionalFields(Reference reference) {
        reference.getOptionalFields().forEach(io::print);
    }

    /**
     * Adds a single field to a reference
     * @param reference The Reference to which to add the field
     */
    private void addOptionalFieldToReference(Reference reference) {
        String fieldName;

        // TODO should have some "backend" validation also (e.g. exception from Reference)
        do {
            fieldName = io.readLine("Field:");
        } while (!reference.getOptionalFields().contains(fieldName));

        String value = io.readLine("Value for " + fieldName);
        reference.setField(fieldName, value);
    }

    /**
     * Asks the user for value for a given field name.
     * @param fieldName Name of the field for which to ask the value
     * @return Value for the field
     */
    private String promptForField(String fieldName) {
        return io.readLine(fieldName + ":");
    }

}