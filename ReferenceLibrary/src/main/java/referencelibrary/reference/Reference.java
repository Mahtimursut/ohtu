/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.reference;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.TreeSet;

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
    private final Fields requiredFields;
    private final Fields optionalFields;
    private final TreeMap<String, String> fieldValues;

    /**
     * Creates a new reference with given type and fields
     *
     * @param referenceType
     * @param requiredFields
     * @param optionalFields
     */
    public Reference(ReferenceType referenceType, Fields requiredFields, Fields optionalFields) {
        this.referenceType = referenceType;
        this.requiredFields = requiredFields;
        this.optionalFields = optionalFields;
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

    /**
     * Removes the value corresponding a field
     *
     * <p>The removal will fail if trying to remove a required field.</p>
     *
     * @param fieldName Field for which to remove the value
     * @return true if the value was removed, false otherwise
     */
    public boolean removeField(String fieldName) {
        if (getRequiredFields().contains(fieldName)) return false;
        fieldValues.remove(fieldName);
        return true;
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
     * Returns required fields for this reference
     *
     * @return
     */
    public Fields getRequiredFields() {
        return requiredFields;
    }

    /**
     * Returns optional fields for this reference
     *
     * @return
     */
    public Fields getOptionalFields() {
        return optionalFields;
    }
    
    /**
     * Returns the author/editor and title
     *
     * @return titleAndAuthor
     */
    public String getReferenceAsFormattedText() {
        return "[" + getReferenceName() + "] " + getAuthorOrEditor() + ": " + getTitle();
    }
    
    /**
     * Returns author/editor (the one in required fields)
     *
     * @return AuthorOrEditor
     */
    public String getAuthorOrEditor() {
        String AuthorOrEditor="";
        if (requiredFields.contains("author") && fieldValues.get("author")!=null) {
            AuthorOrEditor = fieldValues.get("author");
        } else if (requiredFields.contains("editor") && fieldValues.get("editor")!=null){
            AuthorOrEditor = fieldValues.get("editor");
        }
        return AuthorOrEditor;
    }
    
    /**
     * Returns the title
     * If title is null, returns empty string
     *
     * @return title
     */
    public String getTitle() {
        if (fieldValues.get("title")!=null) {
            return fieldValues.get("title");
        }

        return "";
    }
    
    /**
     * Returns all possible fields for this reference
     *
     * @return list of fields
     */
    public TreeSet<String> getAllFields() {
        TreeSet<String> fields = new TreeSet<>();
        fields.addAll(getRequiredFields().getFields());
        fields.addAll(getOptionalFields().getFields());
        return fields;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Reference && ((Reference) object).referenceName.equalsIgnoreCase(this.referenceName);
    }
}
