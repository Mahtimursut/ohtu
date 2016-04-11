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
        UI terminalUI = new UI(new ConsoleIO());
        terminalUI.run();
    }
    
    
    
}
