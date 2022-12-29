package pl.edu.agh.kis;
import java.util.ArrayList;

/**
 * Main class for running program.
 * @author Jakub Głowacki
 */
public class Main {
    /**
     * Main method for running program.
     * Initializes library,
     * Creates ArrayList for readers and writers.
     * Creates readers and writers then add them to ArrayList.
     * Starts all readers and writers.
     * @param args - optional arguments from command line
     */
    public static void main(String[] args) {
        /*Dopisane na szybko do testów*/
        int readersNumber = 10;
        int writersNumber = 3;
        if (args.length == 2)  {
            readersNumber = Integer.parseInt(args[0]);
            writersNumber = Integer.parseInt(args[1]);
        }
        //Initialize library
        Library library = new Library();
        //Create ArrayList for readers and writers
        ArrayList<Reader> readers = new ArrayList<>(readersNumber);
        ArrayList<Writer> writers = new ArrayList<>(writersNumber);
        //Add newly created readers and writers to ArrayLists
        for(int i = 0; i < readersNumber; i++){
            readers.add(new Reader(library, i));
        }
        for(int i = 0; i < writersNumber; i++){
            writers.add(new Writer(library, i));
        }
        //Start running readers and writers
        for(Reader reader : readers){
            reader.start();
        }
        for(Writer writer : writers){
            writer.start();
        }

    }

}
