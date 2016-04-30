package referencelibrary.reference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by petri on 21.4.2016.
 *
 * This test class contains unit tests that are common for all reference types.
 */
@RunWith(value = Parameterized.class)
public class ReferenceTest {

    private Class referenceClass;
    private Reference reference;

    public ReferenceTest(Class c) {
        this.referenceClass = c;
    }

    /**
     * Parameterizes the tests with defined reference types.
     * @return Reference implementation classes to test
     */
    @Parameterized.Parameters
    public static Collection referenceTypes() {
        Collection<Class> referenceTypes = new HashSet<>();
        referenceTypes.add(BookReference.class);
        referenceTypes.add(ArticleReference.class);
        referenceTypes.add(InproceedingsReference.class);
        return referenceTypes;
    }

    @Before
    public void setUp() throws Exception {
        reference = (Reference)referenceClass.getDeclaredConstructor().newInstance();
    }

    @Test
    public void testSetField() {
        String fieldName = "author";
        String expectedValue = "Risto Mikkola";
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));

        expectedValue = "Jukka-Pekka Moilanen";
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));
    }

    @Test
    public void testSetFieldOptional() {
        String fieldName = "note";
        String expectedValue = "great edition";
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));

        expectedValue = "great edition indeed";
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));
    }

    @Test
    public void testGetField() {
        String fieldName = "author";
        String expectedValue = "Sami Sarsa";
        assertNull(reference.getField(fieldName));
        reference.setField(fieldName, expectedValue);
        assertEquals(expectedValue, reference.getField(fieldName));
    }

    @Test
    public void testGetAllFields() {
        TreeSet<String> expectedFields = new TreeSet<>();
        expectedFields.addAll(reference.getRequiredFields().getFields());
        expectedFields.addAll(reference.getOptionalFields().getFields());
        TreeSet<String> fields = reference.getAllFields();

        assertEquals(expectedFields, fields);
    }

    @Test
    public void removeField() throws Exception {
        List<String> optionals = new ArrayList<>(reference.getOptionalFields().getFields());
        String testField = optionals.get(0);
        reference.setField(testField, "testvalue");
        assertTrue(reference.getFieldValues().keySet().contains(testField));
        reference.removeField(testField);
        assertFalse(reference.getFieldValues().keySet().contains(testField));
    }

    @Test
    public void hasReferenceNameRecognizesExactNameToBeRight() {
        String correctName = "exactName 123";
        reference.setReferenceName(correctName);
        assertTrue(reference.hasReferenceName(correctName));
    }

    @Test
    public void hasReferenceNameRecognizesDifferentCaseNameToBeRight() {
        String correctName = "exactName 123";
        String differentCaseName = "EXACTNAME 123";
        reference.setReferenceName(correctName);
        assertTrue(reference.hasReferenceName(differentCaseName));
    }

    @Test
    public void hasReferenceNameRecognizesInvalidNameToBeWrong() {
        String correctName = "exactName 123";
        String invalidName = "invalid Name";
        reference.setReferenceName(correctName);
        assertFalse(reference.hasReferenceName(invalidName));
    }
}