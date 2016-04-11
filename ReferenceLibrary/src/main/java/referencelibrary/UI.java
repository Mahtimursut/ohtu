/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

/**
 *
 * @author juhapekm
 */
public class UI {
    private IO io;
    
    public void run() {
        io = new ConsoleIO();
        io.print("Reference library");
        printAvailableCommands();
        String command = "q";
        command = io.readLine("\nChoose command: \n");
        while(!command.equals("q")) {
            //call chosen functionality
            switch (command){
                case "a":   addNewReference();
                            break;
                case "g":   generateBixTexFile();
                            break;
            }
            //new command
            printAvailableCommands();
            command = io.readLine("\nChoose command: \n");
        }
        io.print("Ohjelma loppuu..");
    }
    
    public void printAvailableCommands() {
        io.print("(q)uit," + "\n" + "(a)dd new reference," + "\n" + "(g)enerate bibtex" + "\n");
    }
    
    public void addNewReference() {
        io.print("Add new reference");
        //switch: reference type
            //call appropriate adding method
        io.print("--adding not implement");
        
        io.print("Reference added!");
    }
    
    public void generateBixTexFile() {
        io.print("Generating bibTex -file");
        //call bibtex-file generator
        io.print("--generating not implemented");
        
        io.print("Generating done!");
    }
}
