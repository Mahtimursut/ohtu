/**
 * Created by petri on 24.4.2016.
 */

import referencelibrary.io.StubIO
import referencelibrary.App
import referencelibrary.data.StubDao
import referencelibrary.ui.UI

description 'References are checked for duplicate names'

scenario "a reference with a unique name is stored", {
    given 'a unique name and other required fields are entered for a book', {
        io = new StubIO("a", "b", "a", "name", "1", "2", "3", "4", "n", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
    when 'reference is stored', {
        ui.run()
    }
    then 'storing reference succeeds', {
        reflist = app.listReferences()
        name = reflist.get(1).getReferenceName()
        ensure(name) { isEqualTo "name" }
    }
}

scenario "the user is prompted for a new name when a duplicate name is entered", {
    given 'two references are being created', {
        io = new StubIO("a", "b", "a", "name", "1", "2", "3", "4", "n",
                "a", "b", "a", "name", "newname", "1", "2", "3", "4", "n",
                "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
    when 'same names are entered', {
        ui.run()
    }
    then 'the user is prompted for a new name for the second reference ' +
            'and the reference is stored with that name', {
        io.getPrints().shouldHave("Reference name must be unique.")
        reflist = app.listReferences()
        name = reflist.get(2).getReferenceName()
        ensure(name) { isEqualTo "newname" }
    }
}

scenario "the user is prompted for a new name when a duplicate name in different case is entered", {
    given 'two references are being created', {
        io = new StubIO("a", "b", "a", "name", "1", "2", "3", "4", "n",
                "a", "b", "a", "NAME", "newname", "1", "2", "3", "4", "n",
                "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
    when 'same names are entered in different case', {
        ui.run()
    }
    then 'the user is prompted for a new name for the second reference ' +
            'and the second reference is stored with that name', {
        io.getPrints().shouldHave("Reference name must be unique.")
        reflist = app.listReferences()
        name = reflist.get(2).getReferenceName()
        ensure(name) { isEqualTo "newname" }
    }
}

