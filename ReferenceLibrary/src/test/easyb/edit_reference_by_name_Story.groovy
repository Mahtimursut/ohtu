/**
 * @author rimi
 */

import referencelibrary.io.StubIO
import referencelibrary.App
import referencelibrary.data.StubDao
import referencelibrary.ui.UI

description """A reference can be fetched by name and then it's required and
            optional fields can be edited"""

scenario "attempting to edit nonexisting reference prints that the reference does not exist", {
    given 'edit reference is selected and reference name is typed', {
        io = new StubIO("e", "nonexistent", "q")
        dao = new StubDao()
        app = new App(dao)
        ui = new UI(io, app)
    }

    when 'nonexisting reference name is entered', {
        ui.run()
    }

    then 'user is notified about reference missing', {
        io.getPrints().shouldHave("Reference named nonexistent was not found.")
    }
}

scenario "editing a required field, title, takes effect", {
    given 'edit reference is selected and reference name is typed', {
        io = new StubIO("e", "REF", "r", "new title", "", "q")
        dao = new StubDao()
        dao.find("REF").setField("title", "test title")
        app = new App(dao)
        ui = new UI(io, app)
    }

    when 'existing reference name is entered and new title is given', {
        ui.run()
    }

    then 'the name changes', {
        io.getPrints().shouldHave("Editing reference [Book: REF]")
        io.getPrints().shouldHave("Field title updated")
        io.getPrints().shouldHave("Changes to REF saved!")
        dao.find("REF").getField("title").shouldBe "new title"
    }
}

scenario "editing an optional field, note, takes effect", {
    given 'edit reference is selected and reference name is typed', {
        io = new StubIO("e", "REF", "r", "new note", "", "q")
        dao = new StubDao()
        dao.find("REF").setField("note", "test note")
        app = new App(dao)
        ui = new UI(io, app)
    }

    when 'existing reference name is entered and new note is given', {
        ui.run()
    }

    then 'the note changes', {
        io.getPrints().shouldHave("Editing reference [Book: REF]")
        io.getPrints().shouldHave("Field note updated")
        io.getPrints().shouldHave("Changes to REF saved!")
        dao.find("REF").getField("note").shouldBe "new note"
    }
}

scenario "removing a required field, title, not possible", {
    given 'edit reference is selected and reference name is entered', {
        io = new StubIO("e", "REF", "", "", "q")
        dao = new StubDao()
        dao.find("REF").setField("title", "test title")
        app = new App(dao)
        ui = new UI(io, app)
    }

    when 'existing reference name is entered and required fields are viewed', {
        ui.run()
    }

    then 'removing field is not an option', {
        io.getPrints().shouldHave("Editing reference [Book: REF]")
        io.getPrints().shouldNotHave("(d)elete field")
        io.getPrints().shouldNotHave("Field title removed!")
        dao.find("REF").getField("title").shouldBe "test title"
    }
}

scenario "removing an optional field, note, removes the associated value from the reference", {
    given 'edit reference is selected and reference has note field and reference name is typed', {
        io = new StubIO("e", "REF", "d", "", "q")
        dao = new StubDao()
        dao.find("REF").setField("note", "test note")
        app = new App(dao)
        ui = new UI(io, app)
    }

    when 'existing reference name is entered and note field is removed', {
        ui.run()
    }

    then "note field's value is deleted from reference", {
        io.getPrints().shouldHave("Editing reference [Book: REF]")
        io.getPrints().shouldHave("(d)elete field")
        io.getPrints().shouldHave("Field note removed!")
        dao.find("REF").getField("note").shouldBe null
    }
}
