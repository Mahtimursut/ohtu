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
public class AddBookCommandTest {

    AddReferenceCommand addRefCmd = null;
    App app = null;

    @Before
    public void setUp() {
        this.app = new App(new StubDao());
    }

    /**
     * Create StubIO to simulate add new inproceedings -functionality.
     */
    private StubIO createStubIO() {
        StubIO stubIo = new StubIO(
                "someId",
                "a",
                "someAuthor",
                "someTitle",
                "somePublisher",
                "someYear",
                "y", //answers: "Would you like to add some optional fields?"
                "a", //answers: (a)dd a field
                "note",
                "someNote",
                "s", // just for linecoverage, testing with easyB
                "d"
        );
        return stubIo; //author, title, booktitle, year
    }

    private StubIO createStubIOWithEditor() {
        StubIO stubIo = new StubIO(
                "someId",
                "e",
                "someEditor",
                "someTitle",
                "somePublisher",
                "someYear",
                "n"
        );
        return stubIo; //author, title, booktitle, year
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class AddReferenceCommand.
     */
    @Test
    public void testExecuteWithAuthorSelected() {
        this.addRefCmd = new AddBookCommand(this.app, createStubIO());
        this.addRefCmd.execute(); //this.app.listReferences().get(1).getField("author")
        assertEquals("someAuthor", this.app.listReferences().get(1).getField("author"));
        assertEquals("someTitle", this.app.listReferences().get(1).getField("title"));
        assertEquals("somePublisher", this.app.listReferences().get(1).getField("publisher"));
        assertEquals("someYear", this.app.listReferences().get(1).getField("year"));
        assertEquals("someNote", this.app.listReferences().get(1).getField("note"));
    }

    @Test
    public void testExecuteWithEditor() {
        this.addRefCmd = new AddBookCommand(this.app, createStubIOWithEditor());
        this.addRefCmd.execute(); //this.app.listReferences().get(1).getField("author")
        assertEquals("someEditor", this.app.listReferences().get(1).getField("editor"));
        assertEquals("someTitle", this.app.listReferences().get(1).getField("title"));
        assertEquals("somePublisher", this.app.listReferences().get(1).getField("publisher"));
        assertEquals("someYear", this.app.listReferences().get(1).getField("year"));
    }

    @Test
    public void testEditorOrAuthorAsObligatoryField() {
        this.addRefCmd = new AddBookCommand(this.app, createStubIO());
        this.addRefCmd.execute();
        assertEquals(null, this.app.listReferences().get(1).getField("editor"));
        assertEquals("someAuthor", this.app.listReferences().get(1).getField("author"));
    }

    @Test
    public void testEditorOrAuthorAsDefaultObligatoryField() {
        StubIO stubIO = new StubIO("id", "something", "someAuthor", "2", "3", "4", "n");
        this.addRefCmd = new AddBookCommand(this.app, stubIO);
        this.addRefCmd.execute();
        assertEquals(null, this.app.listReferences().get(1).getField("editor"));
        assertEquals("someAuthor", this.app.listReferences().get(1).getField("author"));
        assertEquals(true, stubIO.getPrints().contains("author selected as default"));
    }
    
    @Test
    public void testEditorAsObligatoryField() {
        this.addRefCmd = new AddBookCommand(this.app, new StubIO("id", "e", "someEditor", "2", "3", "4", "n"));
        this.addRefCmd.execute();
        assertEquals(null, this.app.listReferences().get(1).getField("author"));
        assertEquals("someEditor", this.app.listReferences().get(1).getField("editor"));
    }

}
