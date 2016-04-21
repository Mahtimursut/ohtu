/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rimi
 */
public class ConsoleIOTest {

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private ConsoleIO io;
    private ByteArrayInputStream in;

    public ConsoleIOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        in = new ByteArrayInputStream("a\nb\n10\n".getBytes());
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(in);
        io = new ConsoleIO();
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
        System.setErr(System.err);
        System.setIn(System.in);
    }

    @Test
    public void printIsAddedToPrints() {
        String expected = "test";
        io.print(expected);
        assertTrue(outContent.toString().contains(expected));
    }

    @Test
    public void readLineReadsCorrectly() {
        assertEquals("a", io.readLine("first"));
        assertEquals("b", io.readLine("second"));
        assertEquals("10", io.readLine("third"));
        assertEquals("", io.readLine("fourth"));
        assertTrue(outContent.toString().contains("first"));
        assertTrue(outContent.toString().contains("second"));
        assertTrue(outContent.toString().contains("third"));
        assertTrue(outContent.toString().contains("fourth"));
    }

}
