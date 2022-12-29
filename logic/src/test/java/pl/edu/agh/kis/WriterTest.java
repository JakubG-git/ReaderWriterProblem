package pl.edu.agh.kis;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
/**
 * Class for testing Writer
 * @author Jakub Głowacki
 */
public class WriterTest {
    /**
     * Test for Writer
     * Tests Constructor and start method
     */
    @Test
    public void testWriter() {
        Library library = new Library();
        Writer writer = new Writer(library, 1);
        assertNotEquals(null, writer);
        writer.start();
    }
    /**
     * Test for randomTime method
     * Tests if randomTime method returns different values for different parameters
     */
    @Test
    public void testRandom(){
        Library library = new Library();
        Writer writer = new Writer(library, 1);
        int ra = writer.randomTime(3000, 1000);
        int rb = writer.randomTime(3000, 0);
        assertNotEquals(ra, rb);
    }
    /**
     * Test for run method
     * Tests if run method works correctly
     * Test sleep interrupted exception
     */
    @Test
    public void testWriterRun(){
        Library library = new Library();
        assertNotEquals(null, library);
        ArrayList<Writer> writers = new ArrayList<>(3);
        for(int i = 0; i < 3; i++){
            writers.add(new Writer(library, i));
        }
        for(Writer writer : writers){
            writer.start();
        }

        for(Writer writer : writers){
            writer.interrupt();
        }
    }
}
