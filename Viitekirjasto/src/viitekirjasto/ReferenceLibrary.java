/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viitekirjasto;

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
        System.out.println("Choose command: ");
        String command = "q";
        command = user.nextLine();
        while(!command.equals("q")) {
            //call chosen functionality
            System.out.println("not implemented");
            System.out.println("Choose command: ");
            command = user.nextLine();
        }
        System.out.println("Ohjelma loppuu..");
    }
    
}
