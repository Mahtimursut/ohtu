/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import referencelibrary.data.FileReferenceDao;
import referencelibrary.data.ReferenceDao;
import referencelibrary.io.ConsoleIO;
import referencelibrary.io.IO;
import referencelibrary.ui.UI;

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
        ReferenceDao references = new FileReferenceDao("saved_references");
        App app = new App(references);
        IO io = new ConsoleIO();
        UI terminalUI = new UI(io, app);
        terminalUI.run();
    }
    
    
    
}
