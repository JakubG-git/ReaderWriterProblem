package pl.edu.agh.kis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Class for testing library
 * @author Jakub Głowacki
 */
public class LibraryTest {
    /**
     * Test for Library
     * Tests if methods work.
     */
    @Test
    public void testLibrary() {
        Library library = new Library();
        assertNotEquals(null, library);
        library.requestRead(1);
        library.finishRead();
        library.requestWrite(1);
        library.finishWrite();
    }
}
