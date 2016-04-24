/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.ui.command;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import referencelibrary.App;
import referencelibrary.data.StubDao;
import referencelibrary.io.StubIO;

/**
 *
 * @author sjsarsa
 */
public class AddArticleCommandTest {

    AddReferenceCommand addRefCmd = null;
    App app = null;

    @Before
    public void setUp() {
        this.app = new App(new StubDao());
        this.addRefCmd = new AddArticleCommand(this.app, createStubIO());
    }

    /**
     * Create StubIO to simulate add new inproceedings -functionality.
     */
    private StubIO createStubIO() {
        StubIO stubIo = new StubIO(
                "someId",
                "someAuthor",
                "someJournal",
                "someTitle",
                "someVolume",
                "someYear",
                "y", //answers: "Would you like to add some optional fields?"
                "a", //answers: (a)dd a field
                "note",
                "someNote"
        );
        return stubIo; //author, title, booktitle, year
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class AddReferenceCommand.
     */
//    @Test
    public void testExecute() {
        this.addRefCmd.execute(); //this.app.listReferences().get(1).getField("author")
        assertEquals("someAuthor", this.app.listReferences().get(1).getField("author"));
        assertEquals("someJournal", this.app.listReferences().get(1).getField("journal"));
        assertEquals("someNote", this.app.listReferences().get(1).getField("note"));
        assertEquals("someTitle", this.app.listReferences().get(1).getField("title"));
        assertEquals("someVolume", this.app.listReferences().get(1).getField("volume"));
        assertEquals("someYear", this.app.listReferences().get(1).getField("year"));
    }
}
