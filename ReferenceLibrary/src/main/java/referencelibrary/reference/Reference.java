/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * A general reference class for bibtex references.
 *
 * The classes that implement this class define the required and optional fields
 * of the reference type they represent.
 *
 * @author rimi
 */
public abstract class Reference implements Serializable {

    private final ReferenceType referenceType;
    private String referenceName;
    private final TreeMap<String, String> fieldValues;

    /**
     * Creates a new reference of given type with given name
     *
     * @param referenceType
     * @param referenceName
     */
    public Reference(ReferenceType referenceType, String referenceName) {
        this.referenceType = referenceType;
        this.referenceName = referenceName;
        this.fieldValues = new TreeMap<>();
    }

    /**
     * Sets the value for the field name if the field exists for this reference
     *
     * @param fieldName
     * @param fieldValue
     */
    public void setField(String fieldName, String fieldValue) {
        if (getRequiredFields().contains(fieldName)
                || getOptionalFields().contains(fieldName)) {
            fieldValues.put(fieldName, fieldValue);
        }
    }

    /**
     * Returns the stored value for the field or null
     *
     * @param fieldName
     * @return
     */
    public String getField(String fieldName) {
        return fieldValues.get(fieldName);
    }

    @Override
    public String toString() {
        return "[" + getReferenceType() + ": " + getReferenceName() + "]";
    }

    /**
     * Returns the reference name
     *
     * @return
     */
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * Sets the reference name
     *
     * @param referenceName
     */
    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    /**
     * Gets the type of the reference, for example book, article
     *
     * @return the referenceType
     */
    public ReferenceType getReferenceType() {
        return referenceType;
    }

    /**
     * Returns a map of field names mapped to field values
     *
     * @return fieldValues
     */
    public TreeMap<String, String> getFieldValues() {
        return fieldValues;
    }

    /**
     * Returns a list of all required fields for this reference
     *
     * @return
     */
    abstract public List<String> getRequiredFields();

    /**
     * Returns a list of all optional fields for this reference
     *
     * @return
     */
    abstract public List<String> getOptionalFields();

    /**
     * Returns a list of all possible fields for this reference
     *
     * @return list of fields
     */
    public List<String> getAllFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.addAll(getRequiredFields());
        fields.addAll(getOptionalFields());
        return fields;
    }

    public boolean equals(Object object) {
        return object instanceof Reference && ((Reference) object).referenceName.equals(this.referenceName);
    }
}
