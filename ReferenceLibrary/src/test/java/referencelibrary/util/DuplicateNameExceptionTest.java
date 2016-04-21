package referencelibrary.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by petri on 21.4.2016.
 */
public class DuplicateNameExceptionTest {

    @Test
    public void testConstructor() throws Exception {
        DuplicateNameException ex = new DuplicateNameException("message");
        assertEquals(null, "message", ex.getMessage());
        assertTrue(Exception.class.isAssignableFrom(ex.getClass()));
    }
}