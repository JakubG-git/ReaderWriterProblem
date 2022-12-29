package pl.edu.agh.kis;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Class for testing Reader
 * @author Jakub Głowacki
 */
public class ReaderTest {
    /**
     * Test for Reader
     * Tests Constructor and start method
     */
    @Test
    public void testReader() {
        Library library = new Library();
        Reader reader = new Reader(library, 1);
        assertNotEquals(null, reader);
        reader.start();
        Thread.currentThread().interrupt();
    }
    /**
     * Test for randomTime method
     * Tests if randomTime method returns different values for different parameters
     */
    @Test
    public void testRandom(){
        Library library = new Library();
        Reader reader = new Reader(library, 1);
        int ra = reader.randomTime(3000, 1000);
        int rb = reader.randomTime(3000, 0);
        assertNotEquals(ra, rb);
    }
    /**
     * Test for run method
     * Tests if run method works correctly
     * Test sleep interrupted exception
     */
    @Test
    public void testReaderRun()  {
        Library library = new Library();
        assertNotEquals(null, library);
        ArrayList<Reader> readers = new ArrayList<>(6);
        for(int i = 0; i < 6; i++){
            readers.add(new Reader(library, i));
        }
        for(Reader reader : readers){
            reader.start();
        }
        for(Reader reader : readers){
            reader.interrupt();
        }


    }

}
