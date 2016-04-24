/**
 * Created by petri on 18.4.2016.
 */

import referencelibrary.io.StubIO
import referencelibrary.App
import referencelibrary.data.StubDao
import referencelibrary.ui.UI

description 'A reference is added with proper fields'

scenario "book reference contains all required fields", {
    given 'adding a book is selected and reference info is typed', {
        io = new StubIO("a", "b", "1", "a", "2", "3", "4", "5", "n", "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }

    when 'required fields are entered', {
        ui.run()
    }

    then 'reference contains required fields', {
        reflist = app.listReferences()
        fields = reflist.get(1).getFieldValues()
        ensure(fields) {
            has([author:"2", publisher:"3", title:"4", year:"5"])
        }
    }
}

scenario "book reference contains given optional fields", {
    given 'adding a book is selected and reference info is typed', {
        io = new StubIO("a", "b", "1", "e", "2", "3", "4", "5", 
                "y", "a", "volume", "7", "a", "number", "8", "d",
                "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }

    when 'required and some optional fields are entered', {
        ui.run()
    }

    then 'reference contains the optional fields', {
        reflist = app.listReferences()
        fields = reflist.get(1).getFieldValues()
        ensure(fields) {
            has([volume:"7", number:"8"])
        }
    }
}