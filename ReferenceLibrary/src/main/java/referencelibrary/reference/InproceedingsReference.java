package referencelibrary.reference;

import static referencelibrary.reference.ReferenceType.REFERENCE_INPROCEEDINGS;

/**
 *
 * @author juhapekm
 */
public class InproceedingsReference extends Reference {

    /**
     * Creates a new inproceedings reference with given name
     */
    public InproceedingsReference() {
        super(REFERENCE_INPROCEEDINGS,
                new Fields(
                        "author",
                        "title",
                        "booktitle",
                        "year"
                ),
                new Fields(
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
                        "key"
                )
        );
    }

}
