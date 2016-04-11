/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * An utility class for writing a
 *
 * @author rimi
 */
public class FileWriter {

    /**
     * Creates a file or overwrites existing file with given content
     *
     * @param fileName name of the file to write to
     * @param content content of the file
     */
    static void Write(String fileName, String content) {
        PrintWriter writer = getWriter(fileName);
        writer.println(content);
        writer.close();
    }

    /**
     * Gets a writer to be used to write to the given filename.
     *
     * @param fileName name of the file to write to
     * @return
     */
    protected static PrintWriter getWriter(String fileName) {
        try {
            return new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            System.out.println("Could not open file to write to: " + ex.getMessage());
        }
        return null;
    }
}
