/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.reference;

/**
 *
 * @author rimi
 */
public enum ReferenceType {
    REFERENCE_BOOK("Book"),
    REFERENCE_ARTICLE("Article");

    private final String typeName;

    /**
     * Creates a new reference type.
     *
     * @param printableName return value of toString
     */
    private ReferenceType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
