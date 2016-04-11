/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary;

import java.util.HashMap;
import java.util.Map;

/**
 * A general reference class for bibtex references
 * 
 * @author rimi
 */
public class Reference {
    private String referenceName;
    private String referenceType;
    private final HashMap<String, String> fieldValues;

    public Reference(String referenceName, String referenceType, HashMap<String, String> fieldValues) {
        this.referenceName = referenceName;
        this.referenceType = referenceType;
        this.fieldValues = fieldValues;
    }

    @Override
    public String toString() {
        String str = getReferenceType()+": "+getReferenceName()+"\n";
        str += "";
        for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
            str += entry.getKey()+": "+entry.getValue()+"\n";
        }
        return str;
    }

    /**
     * Gets the name field
     * @return the referenceName
     */
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * Sets the name field
     * @param referenceName the referenceName to set
     */
    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    /**
     * Gets the type, for example book, article
     * @return the referenceType
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * Sets the reference type, for example book, article
     * @param referenceType the referenceType to set
     */
    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    /**
     * Returns a map of field names mapped to field values
     * @return copy of fieldValues
     */
    public HashMap<String, String> getFieldValuesCopy() {
        return (HashMap<String, String>)fieldValues.clone();
    }
}
