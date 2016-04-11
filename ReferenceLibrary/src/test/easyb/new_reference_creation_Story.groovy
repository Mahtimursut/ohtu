import referencelibrary.*
import referencelibrary.UI.*

description """A new bookreference can be added
                to referencelibrary"""

scenario "New bookreference can be added to system", {
    given 'command add book is selected and reference info is typed', {
        io = new StubIO("a", "B", "1", "2", "3", "4", "5", "q")
        ui = new UI(io)
    }
 
    when 'a valid username and password are entered', {
      ui.run()
    }

    then 'new user is registered to system', {
      io.getPrints().shouldHave("Reference added!")
    }
}
