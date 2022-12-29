package pl.edu.agh.kis;

import java.security.SecureRandom;
/**
 * Class representing writer
 * Writer can request to write in library and finish writing.
 * @author Jakub Głowacki
 */
public class Writer extends Thread{
    /**
     * Library to write in
     */
    private final Library library;
    /**
     * Id of writer
     */
    private final int id;
    /**
     * Logger for logging information about writing
     */
    private static final Logging logger = new Logging(Writer.class.getName());
    /**
     * SecureRandom object for generating random time for writing
     */
    SecureRandom random = new SecureRandom();
    /**
     * Maximum time for writing
     */
    private int maxWritingTime = 3000;
    /**
     * Minimum time for writing
     */
    private int minWritingTime = 1000;

    /**
     * Constructor for Writer class.
     * @param library library to write to
     * @param id id of writer
     */
    public Writer(Library library, int id) {
        this.library = library;
        this.id = id;
    }

    /**
     * Method returning random time for writing/waiting.
     * Uses SecureRandom class.
     * @param max - maximum time
     * @param min - minimum time
     * @return random time between min and max
     */
    protected  int randomTime(int max, int min){
        return (int)(random.nextDouble() * (max - min) + min);
    }

    /**
     * Method for requesting write to library.
     * Overrides run method from Thread class.
     * tries to acquire write lock from library.
     * If it is possible, it starts writing.
     * After writing, it releases write lock.
     * logs information about writing to standard output.
     * if thread is interrupted, it logs information about interruption.
     * @see Thread#run()
     */
    @Override
    public  void run() {
        while (true){
            try {
                Thread.sleep(randomTime(maxWritingTime, 0));
                logger.info("Writer " + id + " wants to write");
                library.requestWrite(id);
                Thread.sleep(randomTime(maxWritingTime, minWritingTime));
                library.finishWrite();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                Thread.currentThread().interrupt();
            }

        }
    }

}
