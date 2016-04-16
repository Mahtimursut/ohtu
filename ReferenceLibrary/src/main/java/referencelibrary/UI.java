/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import referencelibrary.reference.Reference;
import referencelibrary.reference.BookReference;

/**
 *
 * @author juhapekm
 */
public class UI {
    private final IO io;
    private final App app;

    public UI(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    public void run() {
        io.print("Reference library");
        printAvailableCommands();
        String command = "q";
        command = io.readLine("\nChoose command: \n");
        while(!command.equals("q")) {
            //call chosen functionality
            switch (command){
                case "a":
                    addNewReference();
                    break;
                case "g":
                    generateBixTexFile();
                    break;
                case "s":
                    showReferences();
                    break;
            }
            //new command
            printAvailableCommands();
            command = io.readLine("\nChoose command: \n");
        }
        io.print("Ohjelma loppuu..");
    }
    
    public void printAvailableCommands() {
        io.print("(q)uit," + "\n" +
                "(a)dd new reference," + "\n" +
                "(g)enerate bibtex" + "\n" +
                "(s)how references" + "\n");
    }
    
    public void addNewReference() {
        io.print("Add new reference");
        
        //prompt reference type
        String command = io.readLine(
            "\nChoose reference type: " +
                "\n(b)ook, " +
                "\n(a)rticle, " +
                "\n(i)nproceedings, " +
                "\n(o)ther\n\n");
        
        //switch (reference type)
        switch(command){
            case "b":   addNewBook();
                        io.print("Reference added!");
                        break;
            case "a":   //call addNewArticle
                        io.print("Reference added!");
                        break;
            case "i":   //call addNewInproceedings
                        io.print("Reference added!");
                        break;
            case "o":   io.print("--adding other not implement");
                        //io.print("Reference added!");
                        break;
        } 
    }
    
    public void addNewBook() {
        //prompt reference name
        String referenceName = io.readLine("Reference id");

        Reference newRef = new BookReference(referenceName);
        fillReferenceFields(newRef);

        //save the reference
        app.newReference(newRef);
    }

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

    private void fillRequiredFields(Reference reference) {
        reference.getRequiredFields().forEach(field -> reference.setField(field, promptForField(field)));
    }

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

    private void addOptionalFieldToReference(Reference reference) {
        String fieldName;

        // TODO should have some "backend" validation also (e.g. exception from Reference)
        do {
            fieldName = io.readLine("Field:");
        } while (!reference.getOptionalFields().contains(fieldName));

        String value = io.readLine("Value for " + fieldName);
        reference.setField(fieldName, value);
    }

    private String promptForField(String fieldName) {
        return io.readLine(fieldName + ":");
    }

    private void showValidOptionalFields(Reference reference) {
        reference.getOptionalFields().forEach(io::print);
    }

    public void generateBixTexFile() {
        io.print("\nGenerating bibTex -file");
        //call bibtex-file generator
        app.generateBixTexFile();
        
        io.print("Generating done!\n");
    }

    public void showReferences() {
        app.listReferences().forEach(reference -> io.print(reference.toString()));
    }
}
