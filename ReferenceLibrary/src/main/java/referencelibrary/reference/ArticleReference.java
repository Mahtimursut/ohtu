package referencelibrary.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static referencelibrary.reference.ReferenceType.REFERENCE_ARTICLE;

/**
 *
 * @author juhapekm
 */
public class ArticleReference extends Reference {

    private final String[] requiredFields = {
        "author",
        "title",
        "journal",
        "year",
        "volume",};
    private final String[] optionalFields = {
        "number",
        "pages",
        "month",
        "note",
        "key",
        "publisher", //not in wikipedia listing of optionalFields for article
        "address"}; //not in wikipedia listing of optionalFields for article

    /**
     * Creates a new article reference with given name
     *
     * @param referenceName
     */
    public ArticleReference(String referenceName) {
        super(REFERENCE_ARTICLE, referenceName);
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
