/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A general reference class for bibtex references
 *
 * @author rimi
 */
public class Reference implements Serializable {

    private String referenceType;
    private String referenceName;
    private final HashMap<String, String> fieldValues;

    public Reference(String referenceType, String referenceName) {
        this.referenceType = referenceType;
        this.referenceName = referenceName;
        this.fieldValues = new HashMap<>();
    }

    public void setField(String fieldName, String fieldValue) {
        fieldValues.put(fieldName, fieldValue);
    }

    public String getField(String fieldName) {
        return fieldValues.get(fieldName);
    }

    @Override
    public String toString() {
        String str = getReferenceType() + ": " + getReferenceName() + "\n";
        str += "";
        for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
            str += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return str;
    }

    /**
     * Gets the name field
     *
     * @return the referenceName
     */
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * Sets the name field
     *
     * @param referenceName the referenceName to set
     */
    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    /**
     * Gets the type, for example book, article
     *
     * @return the referenceType
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * Sets the reference type, for example book, article
     *
     * @param referenceType the referenceType to set
     */
    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    /**
     * Returns a map of field names mapped to field values
     *
     * @return fieldValues
     */
    public HashMap<String, String> getFieldValues() {
        return fieldValues;
    }
}
