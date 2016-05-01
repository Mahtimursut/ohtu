/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencelibrary.util;

import java.util.List;
import referencelibrary.io.IO;
import referencelibrary.reference.Reference;

/**
 *
 * @author sjsarsa
 */
public class FieldValidator {
    
    private IO io;
    
    public FieldValidator(IO io) {
        this.io = io;
    }
    
    public boolean fieldNameIsValid(String fieldName, Reference reference) {
        if (!reference.getRequiredFields().contains(fieldName)
                && !reference.getOptionalFields().contains(fieldName)) {
            io.print(fieldName + " is not a valid field");
            return false;
        }
        return true;
    }
    
    public boolean fieldValueIsValid(String fieldValue) {    
        if (fieldValue.isEmpty()) {
            io.print("Field value must not be empty");
            return false;
        }
        return true;
    }
    
    public boolean referenceNameIsUnique(String referenceName, List<Reference> referenceList) {
        for (Reference reference : referenceList) {
            if(reference.hasReferenceName(referenceName)) return false;
        }
        return true;
    }

    public boolean referenceNameIsNotEmpty(String referenceName) {
        return referenceName.trim().length() > 0;
    }
}
