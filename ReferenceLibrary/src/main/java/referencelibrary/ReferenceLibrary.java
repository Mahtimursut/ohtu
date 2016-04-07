/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import java.util.Scanner;

/**
 *
 * @author juhapekm
 */
public class ReferenceLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner userinput = new Scanner(System.in);
        runUI(userinput);
    }
    
    public static void runUI(Scanner user) {
        System.out.println("Viitekirjasto");
        System.out.println("\nChoose command: ");
        printAvailableCommands();
        String command = "q";
        command = user.nextLine();
        while(!command.equals("q")) {
            //call chosen functionality
            switch (command){
                case "a":   addNewReference();
                            break;
                case "g":   generateBixTexFile();
                            break;
            }
            //new command
            System.out.println("\nChoose command: ");
            printAvailableCommands();
            command = user.nextLine();
        }
        System.out.println("Ohjelma loppuu..");
    }
    
    public static void printAvailableCommands() {
        System.out.println("(q)uit," + "\n" + "(a)dd new reference," + "\n" + "(g)enerate bibtex" + "\n");
    }
    
    public static void addNewReference() {
        System.out.println("Add new reference");
        //switch: reference type
            //call appropriate adding method
        System.out.println("--adding not implement");
        
        System.out.println("Reference added!");
    }
    
    public static void generateBixTexFile() {
        System.out.println("Generating bibTex -file");
        //call bibtex-file generator
        System.out.println("--generating not implemented");
        
        System.out.println("Generating done!");
    }
    
}
