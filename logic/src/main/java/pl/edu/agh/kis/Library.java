package pl.edu.agh.kis;

import java.util.concurrent.Semaphore;
/**
 * Class representing library
 * Library object.
 * Can have only 5 readers or 1 writer at the same time.
 * Makes use of 4 semaphores.
 * @author Jakub Głowacki
 */
public class Library{
    /**
     * Maximum number of readers
     */
    private static final int MAX_CAPACITY = 5;
    /**
     * Logger for logging information about library
     */
    private static final Logging logger = new Logging(Library.class.getName());
    /**
     * Semaphore for library and queue (a.k.a. whole library + queue outside it)
     * It is used to make sure that only 5 readers or 1 writer can be in library at the same time.
     * This semaphore is fair, so threads are served in order of their request.
     */
    static Semaphore libraryAndQueue = new Semaphore(1,true);
    /**
     * Semaphore for library (a.k.a. only library)
     * It is used to make sure that only 5 readers can be in library at the same time.
     * This semaphore is fair, so threads are served in order of their request.
     */
    static Semaphore libraryLock = new Semaphore(MAX_CAPACITY,true);
    /**
     * Semaphore for writer lock
     * It is used to make sure that only 1 writer can be in library at the same time.
     * It is also used to make sure that writer can't enter library if there are readers inside.
     * This semaphore is fair, so threads are served in order of their request.
     */
    static Semaphore writeLock = new Semaphore(1, true);
    /**
     * Semaphore for reader lock
     * It is used to make sure that only 5 readers can be in library at the same time.
     * This semaphore is fair, so threads are served in order of their request.
     */
    static Semaphore readLock = new Semaphore(1, true);

    /**
     * Field for counting readers in library
     */
    private int readCount = 0;
    /**
     * Method for requesting reading in library.
     * Tries to acquire libraryAndQueue lock.
     * Tries to acquire read lock from library.
     * Acquires writer lock to make sure that no writer is in library if there are readers in it.
     * If it is possible, it starts reading.
     * Releases libraryAndQueue lock to allow next readers in queue to read.
     * Releases read lock to allow reading.
     * Acquires libraryLock to make sure that there can be only 5 readers in library at the same time.
     * Logs information about reading and number of readers in library.
     * @param id - id of reader that wants to read
     */
    public void requestRead(int id){
        libraryAndQueue.acquireUninterruptibly();
        readLock.acquireUninterruptibly();
        readCount++;
        if(readCount == 1){
            writeLock.acquireUninterruptibly();
        }
        libraryAndQueue.release();
        readLock.release();
        libraryLock.acquireUninterruptibly();
        logger.info("\t\t[Reader " + id + " is reading]");
        logger.info("\t\t\t(Number of readers: " + readCount+")");
    }
    /**
     * Method for finishing reading in library.
     * Releases libraryLock to allow next readers in queue to read.
     * Acquires read lock to make sure reader finishes reading.
     * Decreases number of readers in library.
     * If there are no readers in library, releases writer lock to allow writer to enter library.
     * Releases read lock to allow reading.
     * Logs information about finishing reading and number of readers in library.
     */
    public  void finishRead() {
        libraryLock.release();
        readLock.acquireUninterruptibly();
        readCount--;
        if(readCount == 0){
            writeLock.release();
        }
        logger.info("Reader " + (Thread.currentThread().getName()).substring(Thread.currentThread().getName().length() -1) + " finished reading");
        logger.info("\t\t\t(Number of readers: " + readCount+")");
        readLock.release();
    }

    /**
     * Method for requesting writing in library.
     * Tries to acquire libraryAndQueue lock.
     * Tries to acquire writer lock from library.
     * If it is possible, it starts writing.
     * Logs information about writing and number of writers in library.
     * @param id - id of writer that wants to write
     */
    public  void requestWrite(int id) {
        libraryAndQueue.acquireUninterruptibly();
        writeLock.acquireUninterruptibly();
        logger.info("\t\t[Writer " + id + " is writing]");
        logger.info("\t\t\t(Number of writers: " + 1 +")");
    }

    /**
     * Method for finishing writing in library.
     * Releases libraryAndQueue lock to allow next person in queue to enter library.
     * Releases writer lock to allow next writers in queue to write.
     * Logs information about writing.
     */
    public  void finishWrite()  {
        libraryAndQueue.release();
        writeLock.release();
        logger.info("Writer " + (Thread.currentThread().getName()).substring(Thread.currentThread().getName().length() -1) + " finished writing");
    }
}
