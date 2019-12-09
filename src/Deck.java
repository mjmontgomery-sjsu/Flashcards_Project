import java.io.*;
import java.util.LinkedList;
import java.nio.file.*;

/**
 * Stores Deck of Flashcards to be used and controls the I/O Functionality
 */
public class Deck {

//Opening Fields

    private LinkedList<Flashcard> flashCard = null;
    private Path pathForCards = null;
    private File deck = null;
    private final String SEPARATOR = "\t";
//


    /**
     * Constructor
     */
    public Deck()
    {
        pathForCards = Paths.get("deck.txt"); // path for text file for the deck
        deck = pathForCards.toFile();
        flashCard= this.getCards();

    }

    /**
     * Gets the Flashcard linked list
     * @return a linked list of flashcards that are to be stored in a text file
     */
    public LinkedList<Flashcard> getCards() {

        if (flashCard != null) return flashCard;
        {

            flashCard = new LinkedList<>();

        }

        if (Files.exists(pathForCards)) {

            try (BufferedReader in =
                         new BufferedReader(
                                 new FileReader(deck))) {
                String line = in.readLine();
                while (line != null) {
                    String[] fields = line.split(SEPARATOR);
                    String question = fields[0];
                    String answer = fields[1];
                   // int interval = Integer.parseInt(fields[2]);
                    boolean beenViewed = Boolean.parseBoolean(fields[2]);
                    Flashcard card = new Flashcard(question, answer, beenViewed );
                    flashCard.add(card);
                    line = in.readLine();
                }
            }
            catch(IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return flashCard;
    }

    /**
     * Overloaded version of getCards with parameters
     * @param newFile which is a new file
     * @return a linked list of flashcards that are saved in a text file when read
     */
    public LinkedList <Flashcard> getCards(File newFile)
   {

        flashCard = new LinkedList<>();

        if (Files.exists(newFile.toPath()))
        {
            try(BufferedReader in = new BufferedReader( new FileReader(newFile)))
            {
                String newLine = in.readLine();

                while (newLine != null)
                {
                    String[] stuff = newLine.split(SEPARATOR);
                    String question = stuff[0];
                    String answer = stuff[1];
                    boolean beenViewed = Boolean.parseBoolean(stuff[2]);

                    Flashcard x = new Flashcard(question, answer, beenViewed);
                    flashCard.add(x);
                    newLine = in.readLine();




                }
            }

            catch(IOException e)
            {
                    e.printStackTrace();
                    return null;
            }
        }

        return flashCard;

    }


    /**
     * Saves current deck to a text file
     * @return true when successful and false when failed.
     */
    public boolean saveCards()
    {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(deck)))){

            for(Flashcard m: flashCard)
            {
                out.print(m.getQuestion() + SEPARATOR);
                out.print(m.getAnswer() + SEPARATOR);
                out.println(m.getView());
            }

        }


        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * Overloading the saveCards method with a parameter
     * Saves deck from file object to a text file
     * @param cards that need to be read
     * @return true if successful and false if failed
     */
    public boolean saveCards(File cards)
    {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(cards)))){
            for(Flashcard m: flashCard)
            {
                out.print(m.getQuestion() + SEPARATOR);
                out.print(m.getAnswer() + SEPARATOR);
                out.println(m.getView());
            }

        }


        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * This method sets a new linked list of cards to the currently selected deck
     * @param abc
     */
    public void setFlashCard(LinkedList<Flashcard> abc)
    {
        flashCard = abc;
    }

   public int getQuestionCount() {
	   return flashCard.size();
   }
   
   public void setCards(LinkedList<Flashcard> newCards) {
       flashCard = newCards;
   }


}
