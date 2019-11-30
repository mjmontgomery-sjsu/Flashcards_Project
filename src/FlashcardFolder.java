import java.util.LinkedList;

/**
 * Stores Decks of Flashcards and is the model for the interface of the
 */
public class FlashcardFolder {

    private Deck deck;
    private LinkedList<Flashcard> deckOfCards;
    private static int counterForCards;
    private Flashcard current;

    public FlashcardFolder()
    {

    }

}
