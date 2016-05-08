/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * An utility class for reading and writing files.
 *
 * @author rimi
 */
public class FileUtil {

    /**
     * Creates a file or overwrites existing file with given content
     *
     * @param fileName name of the file to write to
     * @param content content of the file
     */
    public static void write(String fileName, String content) {
        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            writer.println(content);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            System.out.println("Could not write to " + fileName + ": " + ex.getMessage());
        }
    }

    public static String read(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            if (line != null) {
                sb.append(line);
            }

            line = br.readLine();
            while (line != null) {
                sb.append(System.lineSeparator());
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException ex) {
            System.out.println("Could not read " + fileName + ": " + ex.getMessage());
        }
        return null;
    }
}
