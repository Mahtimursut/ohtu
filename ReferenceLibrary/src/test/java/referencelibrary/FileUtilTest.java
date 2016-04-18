/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import referencelibrary.util.FileUtil;

import static org.junit.Assert.*;

/**
 *
 * @author rimi
 */
public class FileUtilTest {

    private static final String TESTFILENAME_STRING = "test_file.txt";

    public FileUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        new File(TESTFILENAME_STRING).delete();
    }

    @Before
    public void setUp() {
        new File(TESTFILENAME_STRING).delete();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructClass() {
        assertNotNull(new FileUtil());
    }

    @Test
    public void readNonexistingFile() {
        assertNull(FileUtil.Read(TESTFILENAME_STRING));
    }

    @Test
    public void writeAndReadFile() {
        String content = "my\ncontent";
        FileUtil.Write(TESTFILENAME_STRING, content);
        assertEquals(content, FileUtil.Read(TESTFILENAME_STRING));
    }

    @Test
    public void writeInvalidFile() {
        String filename = "invalid/filename";
        FileUtil.Write(filename, "");
    }
}
