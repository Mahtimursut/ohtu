import referencelibrary.*
import referencelibrary.io.StubIO
import referencelibrary.ui.UI
import referencelibrary.ui.UI.*
import referencelibrary.data.StubDao

description """A new reference with scandic letters 
                can be added to referencelibrary"""

scenario "New bookreference with scandic letters is added", {
    given 'command add book is selected and reference info is typed,' +
        'scandic letters are typed as they are on scandic keyboard,' +
        'generate bibtex command is chosen', {
        io = new StubIO("a", 
            "b", 
            "a",
            "äää",
            "kirjoittäjä",
            "julkaisijö",
            "ÅÄÖåäöOtsikko",
            "1111",
            "n",
            "g",
            "skandictest",
            "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
 
    when 'a new reference is created and valid data is entered', {
        ui.run()
    }

    then 'created reference is found from the generated bibtex-file with' +
        'scandic letters correctely encoded', {
    }
}
