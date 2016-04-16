/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.ui;

import referencelibrary.App;
import referencelibrary.IO;
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
        commands.getCommand(HELP_COMMAND).execute();
        String command = "q";
        command = io.readLine("\nChoose command: \n");
        while(!command.equals("q")) {
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
            }
            //new command
            commands.getCommand(HELP_COMMAND).execute();
            command = io.readLine("\nChoose command: \n");
        }
        io.print("Ohjelma loppuu..");
    }
    
    private void addNewReference() {
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
            case "b":
                commands.getCommand(ADD_BOOK_COMMAND).execute();
                io.print("Reference added!");
                break;
            case "a":   //call addNewArticle
                io.print("Reference added!");
                break;
            case "i":   //call addNewInproceedings
                io.print("Reference added!");
                break;
            case "o":
                io.print("--adding other not implement");
                //io.print("Reference added!");
                break;
        } 
    }
}
