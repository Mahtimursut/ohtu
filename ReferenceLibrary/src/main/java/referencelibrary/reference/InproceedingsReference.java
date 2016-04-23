package referencelibrary.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static referencelibrary.reference.ReferenceType.REFERENCE_INPROCEEDINGS;

/**
 *
 * @author juhapekm
 */
public class InproceedingsReference extends Reference {

    private final String[] requiredFields = {
        "author",
        "title",
        "booktitle",
        "year",};
    private final String[] optionalFields = { 
        "editor",
        "volume", //NOTE: volume/number
        "number",
        "series",
        "pages",
        "address",
        "month",
        "organization",
        "publisher",
        "note",
        "key",};

    /**
     * Creates a new inproceedings reference with given name
     *
     * 
     */
    public InproceedingsReference() {
        super(REFERENCE_INPROCEEDINGS);
    }

    @Override
    public List<String> getRequiredFields() {
        return new ArrayList<>(Arrays.asList(requiredFields));
    }

    @Override
    public List<String> getOptionalFields() {
        return new ArrayList<>(Arrays.asList(optionalFields));
    }

}
