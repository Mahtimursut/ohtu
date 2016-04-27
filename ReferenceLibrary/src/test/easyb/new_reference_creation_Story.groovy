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
            "e",
            "SWEBOK",
            "Abram, Aman and More, James W. and Bourgue Pierre and Dupuis Robert",
            "IEEE Computer Society",
            "Guide to the Software Engineering Body of Knownledge",
            "2004",
            "n",
            
            "a",
            "b",
            "a",
            "BA04",
            "Beck, Kent and Andres, Cynthia",
            "Addison-Wesley Professional",
            "Extreme Programming Explained: Embrace Change (2nd Edition)",
            "2004",
            "n",

            "a",
            "i",
            "royce70",
            "Royce, Walker",
            "Proceedings of IEEE WESCON 26",
            "Managing the Development of Large Software Systems",
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
            "Proceedings of the SIGCSE '08",
            "Struggles of new college graduates in their first software development job",
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
            "Communications of ACM",
            "Crossing the software education chasm",
            "55",
            "2012",
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
            "a",
            "Martin09",
            "Martin, Robert C",
            "Prentice Hall",
            "Clean Code: A Handbook of Agile Software Craftsmanshipäöå",
            "2008",
            "n",

            "a",
            "b",
            "a",
            "scrum",
            "Ken Schwaber and Mike Beedle",
            "Prentice Hall",
            "Agile Software Development with SCRUM",
            "2002",
            "n",

            "g",
            "sigproc",
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