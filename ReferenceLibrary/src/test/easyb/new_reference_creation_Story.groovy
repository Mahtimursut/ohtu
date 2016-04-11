import referencelibrary.*
import referencelibrary.UI.*
import referencelibrary.data.StubDao

description """A new bookreference can be added
                to referencelibrary"""

scenario "New bookreference can be added to system", {
    given 'command add book is selected and reference info is typed', {
        io = new StubIO("a", "B", "1", "2", "3", "4", "5", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
 
    when 'a valid username and password are entered', {
      ui.run()
    }

    then 'new user is registered to system', {
      io.getPrints().shouldHave("Reference added!")
    }
}
