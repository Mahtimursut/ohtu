package referencelibrary.ui.command;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import referencelibrary.App;
import referencelibrary.StubIO;
import referencelibrary.data.StubDao;
import referencelibrary.reference.Reference;

/**
 *
 * @author juhapekm
 */
public class AddInproceedingsCommandTest {

    AddReferenceCommand addRefCmd = null;
    App app = null;
    public AddInproceedingsCommandTest() {
    }

//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    @Before
    public void setUp() {
        this.app = new App(new StubDao());
        this.addRefCmd = new AddInproceedingsCommand(this.app, createStubIO());
    }

    /**
     * Create StubIO to simulate add new inproceedings -functionality.
     */
    private StubIO createStubIO() {
        StubIO stubIo = new StubIO(
            "someId",
            "someAuthor",
            "someTitle",
            "someBooktitle",
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
    @Test
    public void testExecute() {
        this.addRefCmd.execute(); //this.app.listReferences().get(1).getField("author")
        assertEquals("someAuthor", this.app.listReferences().get(1).getField("author"));
        //TODO check all fields
    }

}
