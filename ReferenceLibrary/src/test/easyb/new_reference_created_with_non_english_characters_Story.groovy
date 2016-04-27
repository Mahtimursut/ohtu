import referencelibrary.*
import referencelibrary.io.StubIO
import referencelibrary.ui.UI
import referencelibrary.ui.UI.*
import referencelibrary.data.StubDao
import java.io.File;
import java.nio.file.Files;

description 'A new reference with finnish characters can be added to referencelibrary'

scenario "New bookreference with finnish characters can be added to system", {
    given 'command add book is selected and reference info is typed with finnish characters', {
        io = new StubIO("a", 
            "b", 
            "a", 
            "näme",
            "äuthör", 
            "publisher",
            "title", 
            "year", 
            "n", 
            "s",
            "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
 
    when 'a new reference is made and valid data is entered', {
      ui.run()
    }

    then 'new reference is added to the system with correct characters', {
       io.getPrints().shouldHave("[näme] äuthör: title")
    }
}

scenario "Finnish characters in generated BibTeX file have been formatted", {
    given 'generate BibTeX command has been selected after creating a reference containing finnish characters', {
        io = new StubIO("a", 
            "b", 
            "a", 
            "näme",
            "äuthör", 
            "publisher",
            "title", 
            "year", 
            "n", 
            "s",
            "g",
            "test_file",
            "q")
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
 
    when 'a new reference is made and valid data is entered and BibTeX command has been selected', {
      ui.run()
    }

    then 'BibTeX file has correctly formatted characters', {
        File f = new File("test_file.bib");
        Arrays.asList(Files.lines(f.toPath()).toArray()).shouldHave(' author    = {\\"{a}uth\\"{o}r},')
        f.delete()
    }
}


