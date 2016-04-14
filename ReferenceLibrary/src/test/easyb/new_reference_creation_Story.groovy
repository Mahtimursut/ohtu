import referencelibrary.*
import referencelibrary.UI.*
import referencelibrary.data.StubDao

description """A new reference can be added
                to referencelibrary"""

scenario "New bookreference can be added to system", {
    given 'command add book is selected and reference info is typed', {
        io = new StubIO("a", "b", "1", "2", "3", "4", "5", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
 
    when 'a new reference is made and valid data is entered', {
      ui.run()
    }

    then 'new reference is added to the system', {
      io.getPrints().shouldHave("Reference added!")
    }
}
