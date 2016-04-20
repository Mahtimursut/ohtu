/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.reference;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class for handling fields
 *
 * @author rimi
 */
public class Fields implements Serializable {

    private final Set<String> fields = new TreeSet<>();

    /**
     * Creates new Fields with given fields as initial fields to use
     *
     * @param initialFields
     */
    public Fields(String... initialFields) {
        fields.addAll(Arrays.asList(initialFields));
    }

    /**
     * Returns the set of fields
     *
     * @return
     */
    public Set<String> getFields() {
        return fields;
    }

    /**
     * Adds the given field
     *
     * @param fieldName
     */
    public void addField(String fieldName) {
        fields.add(fieldName);
    }

    /**
     * Removes a field with given name if it exists
     *
     * @param fieldName
     */
    public void removeField(String fieldName) {
        fields.remove(fieldName);
    }

    /**
     * Returns true if the given field name is in the set of fields
     *
     * @param fieldName
     * @return
     */
    public boolean contains(String fieldName) {
        return fields.contains(fieldName);
    }
}
