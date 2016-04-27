/**
 * Created by sami on 27.4.2016.
 */

import referencelibrary.io.StubIO
import referencelibrary.App
import referencelibrary.data.*
import referencelibrary.ui.UI

description 'References get loaded from a saved file'

scenario "New reference is created and saved in a file", {
    given 'Reference is created and saved in a file', {
        //create a reference and quit
        io = new StubIO("a", 
            "b", 
            "a", 
            "name",
            "author", 
            "publisher",
            "title", 
            "year", 
            "n", 
            "s",
            "q")
        app = new App(new FileReferenceDao("test_references"))
        ui = new UI(io, app)
    }
    when 'reference is created and show command selected', {
        ui.run()
    }
    then 'the added reference is found', {
        io.getPrints().shouldHave("[name] author: title")
    }
}

scenario "Created reference is loaded from a file", {
    given 'The app is launched again', {
        io = new StubIO(
            "s",
            "q")
        app = new App(new FileReferenceDao("test_references"))
        ui = new UI(io, app)
    }
    when 'show command is selected', {
        ui.run()
    }
    then 'the previously added reference is found', {
        io.getPrints().shouldHave("[name] author: title")
        // remove the the file and references with it created in previous scenario
        File testFile = new File("test_references");
        testFile.delete();
    }
}