/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.ui;

import referencelibrary.App;
import referencelibrary.io.IO;
import referencelibrary.ui.command.CommandFactory;

import static referencelibrary.ui.command.CommandFactory.CommandName.*;

/**
 *
 * @author juhapekm
 */
public class UI {
    private final IO io;
    private final CommandFactory commands;

    public UI(IO io, App app) {
        this.io = io;
        this.commands = new CommandFactory(app, io);
    }
    
    public void run() {
        io.print("Reference library");   
        String command = "";     
        
        do {    
            io.print("");
            commands.getCommand(HELP_COMMAND).execute();
            command = io.readLine("Choose command:");
            io.print("");
            //call chosen functionality
            switch (command){
                case "a":
                    addNewReference();
                    break;
                case "g":
                    commands.getCommand(GENERATE_BIBTEX_COMMAND).execute();
                    break;
                case "s":
                    commands.getCommand(SHOW_REFERENCES_COMMAND).execute();
                    break;
                case "r":
                    commands.getCommand(REMOVE_REFERENCE_COMMAND).execute();
                    break;
                case "e":
                    commands.getCommand(EDIT_REFERENCE_COMMAND).execute();
                    break;
            } 
        } while(!command.equals("q"));
        
        io.print("Program closing..");
    }

    /**
     * Allows the user to add a new reference.
     */
    private void addNewReference() {
        io.print("Add new reference");
        
        //prompt reference type
        String command = io.readLine(
            "\nChoose reference type: " +
                "\n(b)ook, " +
                "\n(a)rticle, " +
                "\n(i)nproceedings, " +
                "\n(o)ther" +
                "\n\nChoose:");
        
        //switch (reference type)
        switch(command){
            case "b":
                commands.getCommand(ADD_BOOK_COMMAND).execute();
                io.print("Reference added!");
                break;
            case "a":
                commands.getCommand(ADD_ARTICLE_COMMAND).execute();
                io.print("Reference added!");
                break;
            case "i":
                commands.getCommand(ADD_INPROCEEDINGS_COMMAND).execute();
                io.print("Reference added!");
                break;
            case "o":
                io.print("--adding other not implemented");
                //io.print("Reference added!");
                break;
        } 
    }
}
