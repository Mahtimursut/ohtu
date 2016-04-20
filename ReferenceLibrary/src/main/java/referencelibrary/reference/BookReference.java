/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.reference;

import static referencelibrary.reference.ReferenceType.REFERENCE_BOOK;

/**
 * A book reference
 *
 * @author rimi
 */
public class BookReference extends Reference {

    /**
     * Creates a new book reference with given name
     */
    public BookReference() {
        super(REFERENCE_BOOK,
                new Fields(
                        "author",
                        "title",
                        "publisher",
                        "year"
                ),
                new Fields(
                        "author",
                        "editor",
                        "volume",
                        "number",
                        "series",
                        "address",
                        "edition",
                        "month",
                        "note",
                        "key"
                )
        );
    }

    public void setEditorAsObligatory() {
        getRequiredFields().removeField("author");
        getRequiredFields().addField("editor");
    }

}
