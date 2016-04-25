import referencelibrary.*
import referencelibrary.io.StubIO
import referencelibrary.ui.UI
import referencelibrary.data.StubDao

description 'Saved references can be viewed on the screen'

scenario "Viewing saved references after creating one new reference", {
    given 'removing reference is selected and reference name is typed', {
        io = new StubIO(
            "a",
            "b",
            "a",
            "SHOWTEST",
            "Abram",
            "IEEE Computer Society",
            "GuideToTesting",
            "2004",
            "n",
            "s",
            "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }

    when 'show references command is selected', {
        ui.run()
    }

    then 'newly added reference is show on the screen', {
        io.getPrints().shouldHave("[SHOWTEST] Abram: GuideToTesting")
    }
}