package referencelibrary.reference;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Classes;
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
        reference = (Reference)referenceClass.getDeclaredConstructor(String.class).newInstance("TestRef");
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
        List<String> expectedFields = new ArrayList<>();
        expectedFields.addAll(reference.getRequiredFields());
        expectedFields.addAll(reference.getOptionalFields());
        List<String> fields = reference.getAllFields();

        Collections.sort(expectedFields);
        Collections.sort(fields);
        assertEquals(expectedFields, fields);
    }

}