/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.util;

import java.util.List;
import referencelibrary.reference.Reference;

/**
 *
 * @author sjsarsa
 */
public class FieldValidator {
    
    Reference reference;

    public FieldValidator(Reference reference) {
        this.reference = reference;
    }
    
    public boolean fieldNameIsValid(String fieldName) {
        if (!reference.getRequiredFields().contains(fieldName)
                && !reference.getOptionalFields().contains(fieldName)) {
            System.out.println(fieldName + " is not a valid field");
            return false;
        }
        return true;
    }
    
    public boolean fieldValueIsValid(String fieldValue) {    
        if (fieldValue.isEmpty()) {
            System.out.println("Field value must not be empty");
            return false;
        }
        return true;
    }
    
    public boolean ReferenceNameIsUnique(String referenceName, List<Reference> referenceList) {
        for (Reference reference : referenceList) {
            if(reference.getReferenceName().equalsIgnoreCase(referenceName)) return false;
        }
        return true;
    }
}
