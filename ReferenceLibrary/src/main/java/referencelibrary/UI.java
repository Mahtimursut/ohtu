/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import java.util.HashMap;

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
        String referenceType = "Book";
        String referenceName = io.readLine("Reference id");
        Reference newRef = new Reference(referenceType, referenceName);
        
        //prompt fields
        String author = io.readLine("Author: ");
        String title = io.readLine("Title: ");
        String year = io.readLine("Year: ");
        String publisher = io.readLine("Publisher: ");
        
        //set fields
        newRef.setField("author", author);
        newRef.setField("title", title);
        newRef.setField("year", year);
        newRef.setField("publisher", publisher);

        //save the reference
        app.newReference(newRef);
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
