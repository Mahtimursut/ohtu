/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.ui.command;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import referencelibrary.App;
import referencelibrary.data.StubDao;
import referencelibrary.io.StubIO;
import referencelibrary.reference.BookReference;
import referencelibrary.util.DuplicateNameException;


/**
 *
 * @author sjsarsa
 */
public class ShowReferencesCommandTest {

    ShowReferencesCommand showRefCmd;
    App app;
    StubIO stubIO;

    public ShowReferencesCommandTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.app = new App(new StubDao());
        this.app.listReferences().get(0).setField("author", "Test Author");
        this.app.listReferences().get(0).setField("title", "Some test title");
        this.stubIO = new StubIO("s");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testExecute() {
        this.showRefCmd = new ShowReferencesCommand(this.app, stubIO);
        this.showRefCmd.execute();
        assertEquals("[REF] Test Author: Some test title", stubIO.getPrints().get(2));
        assertEquals(3, stubIO.getPrints().size());
    }
    
    @Test
    public void testShowNewReference() throws DuplicateNameException {
        this.showRefCmd = new ShowReferencesCommand(this.app, stubIO);
        app.newReference(new BookReference());
        app.listReferences().get(1).setReferenceName("TestName");
        app.listReferences().get(1).setField("author", "New Test Author");
        app.listReferences().get(1).setField("title", "New Test Title");
        this.showRefCmd.execute();
        assertEquals(true, stubIO.getPrints().contains("[TestName] New Test Author: New Test Title"));
        assertEquals(4, stubIO.getPrints().size());
    }

}
