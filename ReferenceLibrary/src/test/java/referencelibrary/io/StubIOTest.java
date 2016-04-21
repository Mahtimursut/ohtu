/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.io;

import java.util.ArrayList;
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
public class StubIOTest {

    private StubIO io;

    public StubIOTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        io = new StubIO("a", "b", "10");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void printIsAddedToPrints() {
        String expected = "test";
        io.print(expected);
        assertTrue(io.getPrints().contains(expected));
    }

    @Test
    public void readLineReadsCorrectly() {
        assertEquals("a", io.readLine("first"));
        assertEquals("b", io.readLine("second"));
        assertEquals("10", io.readLine("third"));
        assertEquals("", io.readLine("fourth"));
        assertTrue(io.getPrints().contains("first"));
        assertTrue(io.getPrints().contains("second"));
        assertTrue(io.getPrints().contains("third"));
        assertTrue(io.getPrints().contains("fourth"));
    }

    @Test
    public void outputInitializedCorrectly() {
        ArrayList<String> prints = io.getPrints();
        assertTrue(prints.isEmpty());
    }
}
