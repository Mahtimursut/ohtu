import referencelibrary.*
import referencelibrary.ui.UI
import referencelibrary.ui.UI.*
import referencelibrary.data.StubDao

description """A new article reference can be added
                to referencelibrary"""

scenario "New article reference can be added to system", {
    given 'command add book is selected and reference info is typed', {
        io = new StubIO("a", "a", "1", "2", "3", "4", "5", "6", "n", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
 
    when 'a new article reference is made and valid data is entered', {
      ui.run()
    }

    then 'new article reference is added to the system', {
      io.getPrints().shouldHave("Reference added!")
    }
}
