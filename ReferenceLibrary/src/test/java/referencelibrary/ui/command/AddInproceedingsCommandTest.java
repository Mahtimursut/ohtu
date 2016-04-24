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
                "someBooktitle",
                "someTitle",
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
        assertEquals("someBooktitle", this.app.listReferences().get(1).getField("booktitle"));
        assertEquals("someNote", this.app.listReferences().get(1).getField("note"));
        assertEquals("someTitle", this.app.listReferences().get(1).getField("title"));
        assertEquals("someYear", this.app.listReferences().get(1).getField("year"));
    }
}
