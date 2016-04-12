package referencelibrary;

import java.util.HashMap;

/**
 * A class for converting references to BibTeX format
 *
 * @author ss
 */
public class BibTeXConverter {

    /**
     * converts references to BibTeX formatted String. No support (yet) for
     * several authors unless pre-formatted with "and" separators.
     *
     * @author ss
     */
    public static String convertToBibTeX(Reference r) {
        StringBuilder sb = new StringBuilder();
        int longestKeyLength = getLongestKeyLength(r);
        //type and name
        sb.append("@")
               .append(r.getReferenceType()).append("{")
               .append(r.getReferenceName()).append(",\n");
        //fields
        for (String s : r.getFieldValues().keySet()) {
            s = s.trim();
            sb.append(" ").append(s);
            //add whitespace
            for (int i = 0; i < longestKeyLength - s.length(); i++) {
                sb.append(" ");
            }

            sb.append(" = {").append(r.getFieldValues().get(s))
                    .append("},\n");
        }
        //end
        sb.append("}");
        return sb.toString();
    }

    private static int getLongestKeyLength(Reference r) {
        int longest = 0;
        for (String s : r.getFieldValues().keySet()) {
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        return longest;
    }
}
