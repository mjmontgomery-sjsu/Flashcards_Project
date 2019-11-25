import java.util.LinkedList;

/**
 * Stores Deck of Flashcards to be used
 */
public class Deck {

//Opening Fields

    private LinkedList<FlashCard> flashCard = null;
    private Path pathForCards = null;
    private file  deck = null;
    private final String SEPARATOR = "\t";
//




    public Deck()
    {
        pathForCards = Paths.get("deck.txt"); // path for text file for the deck
        deck = pathForCards.toFile();
        flashCard= this.getCards();

    }

    public LinkedList<FlashCard> getCards() {

        if (cards != null) return flashCard;

        flashCard = new LinkedList<>();

        if (Files.exists(pathForCards)) {

            try (BufferedReader in =
                         new BufferedReader(
                                 new FileReader(deck))) {
                String line = in.readLine();
                while (line != null) {
                    String[] fields = line.split(SEPERATOR);
                    String question = fields[0];
                    String answer = fields[1];
                   // int interval = Integer.parseInt(fields[2]);
                    boolean beenViewed = Boolean.parseBoolean(fields[3]);
                    FlashCard card = new Card(question, answer, beenViewed );
                    cards.add(card);
                    line = in.readLine();
                }
            }
            catch(IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return cards;
    }








}
