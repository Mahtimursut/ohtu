/**
 * @author rimi
 */

import referencelibrary.io.StubIO
import referencelibrary.App
import referencelibrary.data.StubDao
import referencelibrary.ui.UI

description 'A reference can be removed by name'

scenario "removing by correct name removes reference", {
    given 'removing reference is selected and reference name is typed', {
        io = new StubIO("r", "REF", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }

    when 'correct reference name is entered', {
        ui.run()
    }

    then 'reference has been removed', {
        reflist = app.listReferences()
        io.getPrints().shouldHave("References with given id removed!")
        reflist.isEmpty().shouldBe true
    }
}

scenario "removing by nonexistent name does nothing", {
    given 'removing reference is selected and reference name is typed', {
        io = new StubIO("r", "nonexistent", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }

    when 'nonexisting reference name is entered', {
        ui.run()
    }

    then 'no references have been removed', {
        reflist = app.listReferences()
        io.getPrints().shouldHave("No references with given id found!")
        reflist.size().shouldBe 1
    }
}

scenario "cancelling remove operation cancels removing", {
    given 'removing reference is selected', {
        io = new StubIO("r", "", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }

    when 'enter is pressed', {
        ui.run()
    }

    then 'removing cancels without removing references', {
        reflist = app.listReferences()
        io.getPrints().shouldHave("operation cancelled.")
        reflist.size().shouldBe 1
    }
}
