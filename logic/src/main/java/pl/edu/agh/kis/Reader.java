package pl.edu.agh.kis;

import java.security.SecureRandom;
/**
 * Class representing reader.
 * Reader can request to read from library and finish read.
 * @author Jakub Głowacki
 */
public class Reader extends Thread{
    /**
     * Library to read from
     */
    private final Library library;
    /**
     * Id of reader
     */
    private final int id;
    /**
     * Logger for logging information about reading
     */
    private static final Logging logger = new Logging(Reader.class.getName());
    /**
     * SecureRandom object for generating random time for reading
     */
    SecureRandom random = new SecureRandom();
    /**
     * Maximum time for reading
     */
    private final int maxReadingTime = 3000;
    /**
     * Minimum time for reading
     */
    private final int minReadingTime = 1000;

    /**
     * Constructor for Reader class.
     * @param library library to read from
     * @param id id of reader
     */
    public Reader(Library library, int id) {
        this.library = library;
        this.id = id;
    }

    /**
     * Method returning random time for reading/waiting.
     * Uses SecureRandom class.
     * @param max - maximum time
     * @param min - minimum time
     * @return random time between min and max
     */
    protected  int randomTime(int max, int min){
        return (int) (random.nextDouble() * (max - min) + min);
    }

    /**
     * Method for requesting read from library.
     * Overrides run method from Thread class.
     * tries to acquire read lock from library.
     * If it is possible, it starts reading.
     * After reading, it releases read lock.
     * logs information about reading to standard output.
     * if thread is interrupted, it logs information about interruption.
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(randomTime(maxReadingTime, 0));
                logger.info("Reader " + id + " wants to read");
                library.requestRead(id);
                Thread.sleep(randomTime(maxReadingTime, minReadingTime));
                library.finishRead();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                Thread.currentThread().interrupt();
            }

        }
    }
}
