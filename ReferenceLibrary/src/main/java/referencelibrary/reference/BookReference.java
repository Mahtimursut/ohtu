/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static referencelibrary.reference.ReferenceType.REFERENCE_BOOK;

/**
 * A book reference
 *
 * @author rimi
 */
public class BookReference extends Reference {
    
    private ArrayList<String> requiredFields = new ArrayList(Arrays.asList(
        "author",
        "title",
        "publisher",
        "year"));
    private final String[] optionalFields = {
        "author",
        "editor",
        "volume",
        "number",
        "series",
        "address",
        "edition",
        "month",
        "note",
        "key",};

    public void setEditorAsObligatory() {
        requiredFields.remove("author");
        requiredFields.add(0, "editor");
    }

    /**
     * Creates a new book reference with given name
     *
     * 
     */
    public BookReference() {
        super(REFERENCE_BOOK);
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }

    @Override
    public List<String> getOptionalFields() {
        return new ArrayList<>(Arrays.asList(optionalFields));
    }

}
