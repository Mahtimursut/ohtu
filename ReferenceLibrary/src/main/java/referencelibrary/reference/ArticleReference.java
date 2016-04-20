package referencelibrary.reference;

import static referencelibrary.reference.ReferenceType.REFERENCE_ARTICLE;

/**
 *
 * @author juhapekm
 */
public class ArticleReference extends Reference {

    /**
     * Creates a new article reference with given name
     */
    public ArticleReference() {
        super(REFERENCE_ARTICLE,
                new Fields(
                        "author",
                        "title",
                        "journal",
                        "year",
                        "volume"
                ),
                new Fields(
                        "number",
                        "pages",
                        "month",
                        "note",
                        "key",
                        "publisher", //not in wikipedia listing of optionalFields for article
                        "address" //not in wikipedia listing of optionalFields for article
                )
        );
    }

}
