package referencelibrary.util;

import referencelibrary.reference.Reference;

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
            sb.append(" ").append(stringToBibtexFormat(s));
			sb.append(createWhitespace(longestKeyLength - s.length()));
            sb.append(" = {").append(stringToBibtexFormat(r.getFieldValues().get(s)))
                    .append("},\n");
        }
        //end
        sb.append("}\n\n");
        return sb.toString();
    }
	
    /**
     * Converts a string (field) to bibtex format
     * Conversions include:
     * -conversion of scandinavic letters ä, ö, å 
     * @param s
     * @return formattedS
     */
    public static String stringToBibtexFormat(String s) {
        return convertScandicLetters(s);
    }
    
    public static int getLongestKeyLength(Reference r) {
        int longest = 0;
        for (String s : r.getFieldValues().keySet()) {
            longest = Math.max(longest, s.length());
        }
        return longest;
    }
    
	private static String createWhitespace(int length) {
		return new String(new char[length]).replace('\0', ' ');
	}
	
    private static String convertScandicLetters(String s) {
        s = s.replace("ä", "\\\"{a}").replace("Ä", "\\\"{A}");
        s = s.replace("ö", "\\\"{o}").replace("Ö", "\\\"{O}");
        s = s.replace("å", "\\r{a}").replace("Å", "\\r{A}");
        return s;
    }
}
