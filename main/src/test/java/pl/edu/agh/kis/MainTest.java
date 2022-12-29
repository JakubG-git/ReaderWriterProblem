package pl.edu.agh.kis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Class for testing Main
 * @author Jakub Głowacki
 */
public class MainTest {
    /**
     * Test for Main
     * Tests if main program runs
     */
    @Test
    public void testMain() {
        Main.main(new String[0]);
        assertNotEquals(null, new String[0]);

    }
}
