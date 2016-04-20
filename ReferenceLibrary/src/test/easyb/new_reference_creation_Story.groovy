import referencelibrary.*
import referencelibrary.io.StubIO
import referencelibrary.ui.UI
import referencelibrary.ui.UI.*
import referencelibrary.data.StubDao

description """A new reference can be added
                to referencelibrary"""

scenario "New bookreference can be added to system", {
    given 'command add book is selected and reference info is typed', {
        io = new StubIO("a", 
            "b", 
            "1", 
            "a", 
            "2", 
            "3", 
            "4", 
            "5", 
            "6", 
            "n", 
            "q")
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

scenario "Multiple new references can be added to system", {
    given 'command a book/article/inproceedings is selected multiple times' +
        'and their reference info is typed', {
        io = new StubIO(
            "a",
            "b",
            "SWEBOK",
            "e",
            "Abram, Aman and More, James W. and Bourgue Pierre and Dupuis Robert",
            "Guide to the Software Engineering Body of Knownledge",
            "IEEE Computer Society",
            "2004",
            "n",
            
            "a",
            "b",
            "BA04",
            "a",
            "Beck, Kent and Andres, Cynthia",
            "Extreme Programming Explained: Embrace Change (2nd Edition)",
            "2004",
            "Addison-Wesley Professional",
            "n",

            "a",
            "i",
            "royce70",
            "Royce, Walker",
            "Managing the Development of Large Software Systems",
            "Proceedings of IEEE WESCON 26",
            "1970",
            "y",
            "a",
            "organization",
            "TeX Users Group",
            "a",
            "month",
            "August",
            "d",

            "a",
            "i",
            "Begel_2008",
            "Begel, Andrew and Simon, Beth",
            "Struggles of new college graduates in their first software development job",
            "Proceedings of the SIGCSE '08",
            "2008",
            "y",
            "a",
            "publisher",
            "ACM",
            "d",

            "a",
            "a",
            "fox",
            "Fox, Armando and Patterson, David",
            "Crossing the software education chasm",
            "Communications of ACM",
            "2012",
            "55",
            "y",
            "a",
            "number",
            "5",
            "a",
            "month",
            "may",
            "a",
            "pages",
            "44-49",
            "a",
            "publisher",
            "ACM",
            "a",
            "address",
            "New York, NY, USA",
            "d",
            
            "a",
            "b",
            "Martin09",
            "a",
            "Martin, Robert C",
            "Clean Code: A Handbook of Agile Software Craftsmanship",
            "Prentice Hall",
            "2008",
            "n",
            "a",
            "b",
            "scrum",
            "a",
            "Ken Schwaber and Mike Beedle",
            "Agile Software Development with SCRUM",
            "Prentice Hall",
            "2002",
            "n",

            "g",
            "sigproc.bib",
            "q")	
        app = new App(new StubDao())
        ui = new UI(io, app)
    }
 
    when 'a new reference is made and valid data is entered', {
      ui.run()
    }

    then 'new reference is added to the system and bibtex file is created', {
      io.getPrints().shouldHave("Reference added!")
    }
}