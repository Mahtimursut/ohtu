package referencelibrary.ui.command;

import referencelibrary.io.IO;
import referencelibrary.reference.Reference;
import referencelibrary.util.FieldValidator;

import java.util.Collection;

/**
 * Created by petri on 28.4.2016.
 */
class AddOptionalFieldsSubCommand extends SubCommand {

    AddOptionalFieldsSubCommand(IO io, Reference reference, FieldValidator validator) {
        super(io, reference, validator);
    }

    @Override
    public void execute() {
        String optionalAnswer = io.readLine("Would you like to add some optional fields?\n"
                + "\t(y)es\n"
                + "\t(n)o (default)\n");
        if (optionalAnswer.equalsIgnoreCase("y")) {
            fillOptionalFields(reference);
        }
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
        Collection<String> emptyFields = reference.getOptionalFields().getFields();
        Collection<String> filled = reference.getFieldValues().keySet();
        emptyFields.removeAll(filled);
        if (emptyFields.isEmpty()) io.print("All possible fields are filled.");
        else emptyFields.forEach(io::print);
    }

    /**
     * Adds a single field to a reference
     *
     * @param reference The Reference to which to add the field
     */
    private void addOptionalFieldToReference(Reference reference) {
        String fieldName;
        String fieldValue;
        
        io.print("Enter 'c' to cancel");
        do {
            fieldName = io.readLine("field:");
        } while (!validator.fieldNameIsValid(fieldName, reference) && fieldName.equals("c"));
        
        if (fieldName.equals("c")) return;
        do {
            fieldValue = io.readLine(fieldName + ":");
        } while (!validator.fieldValueIsValid(fieldValue));
        reference.setField(fieldName, fieldValue);
    }
}
