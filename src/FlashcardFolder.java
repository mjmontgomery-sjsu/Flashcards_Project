import java.util.*;
import java.io.*;
import java.text.*;


/**
 * Stores Decks of Flashcards and is the model for the interface of the
 */
public class FlashcardFolder {

    private Deck deck = new Deck();
    private LinkedList<Flashcard> deckOfCards;
    private static int counterForCards;
    private Flashcard current;

    public FlashcardFolder()
    {
        deckOfCards = deck.getCards();
        counterForCards = 0;

        if (getDeckSize() > 0)
        {
            current = deckOfCards.get(counterForCards);
        }

        else
        {
            current = new Flashcard("","", true);
        }

    }

    /**
     *
     * @return the current card being viewed
     */
    public Flashcard returnCurrentCard()
    {
        return current;
    }


    /**
     * The card at the index is retrieved and and the current flashcard is updated
     */
    public void updateCard()
    {
        current = deckOfCards.get(counterForCards);
    }


    /**
     * Creates a new deck by using a LinkedList by reading the data from the file
     * @param file to read in and used to create a new deck
     */
    public void createDeck(File file)
    {
        deckOfCards = deck.getCards(file);
        updateCard();
    }

    /**
     * Return the LinkedList deck
     * @return deckOfCards
     */
    public LinkedList<Flashcard> getDeck()
    {
        return deckOfCards;
    }

    /**
     * Increases the counter
     */
    public void increaseCounter()
    {
        counterForCards++;
    }

    /**
     * Decreases the counter
     */
    public void decreaseCounter()
    {
        counterForCards --;

    }

    /**
     * Makes the counter a specific number
     */
    public void specificCounter(int x)
    {
        counterForCards = x;

    }

    public Flashcard find(String c)
    {
        for (Flashcard flash: deckOfCards)
        {
            if (flash.getQuestion().toLowerCase().contains(toString().toLowerCase())|| flash.getAnswer().toLowerCase().contains(c.toLowerCase()))
            {
                return flash;
            }

        }

        return null;
    }

    /**
     * Gets value of the counter
     * @return counterForCards
     */
    public int getCounterStatus()
    {
        return counterForCards;
    }

    /**
     *The current card will be set ot the next card in the deck
     */
    public void setNext()
    {
        increaseCounter();
        current = deckOfCards.get(counterForCards);
    }


    /**
     * The current card will be set to the previous card
     */
    public void setPrevious()
    {
        decreaseCounter();
        current = deckOfCards.get(counterForCards);
    }


    /**
     * A Flashcard is set as the current Flashcard
     * @param x the Flashcard
     */
    public void setCurrent(Flashcard x)
    {
        current = x;
    }


    /**
     * Sets every card's beenViewed status to false
     */
    public void setNoneViewed()
    {
        for (Flashcard flash: deckOfCards)
        {
            flash.setViewed(false);
        }

    }



    /**
     *
     * @return size of the deck
     */
    public int getDeckSize()
    {
        return deckOfCards.size();
    }


    /**
     * Saves a deck to a file
     * @return
     */
    public boolean save()
    {
        deck.setFlashCard(deckOfCards);
        return deck.saveCards();
    }


    /**
     *Saves a deck to a selected file
     * @return
     */
    public boolean save(File file)
    {

        deck.setFlashCard(deckOfCards);
        return deck.saveCards(file);
    }


    /**
     * Adds card into deck
     * @param stuff is a Flashcard object that needs to be added into the deck
     */
    public void addFlashcard(Flashcard stuff)
    {
        deckOfCards.add(stuff);
    }


    /**
     * Add card into a specific index
     * @param stuff the flashcard
     * @param i the index where the card needs to be added into
     */
    public void addFlashcard(Flashcard stuff, int i)
    {
        deckOfCards.add(i - 1, stuff);
    }


    /**
     * Remove a specific card from our file and deck
     * @param x the Flashcard to be removed
     */
    public void removeFlashcard(Flashcard x)
    {
        deckOfCards.remove(x);

    }


    /**
     * Clears the deck of all FLashcards
     */
    public void clearDeck()
    {
        deckOfCards.clear();
    }



    /**
     * Compares the string parameter with the deck to see is a question return the boolean value
     * @param s to compare strings
     */
    public boolean sameQuestion(String s)
    {
        for(Flashcard flash: deckOfCards)
        {
            if (flash.getQuestion().equalsIgnoreCase(s) )
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Compares the string parameter with the deck to see is a answer return the boolean value
     * @param s to compare strings
     */
    public boolean sameAnswer(String s)
    {
        for(Flashcard flash: deckOfCards)
        {
            if (flash.getAnswer().equalsIgnoreCase(s) )
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Checks to see if cards exist
     * @return int if card exists
     */
    public int read()
    {
        if(deck.getCards() == null)
        {
            return -1;
        }
        if(this.getDeckSize() == 0)
        {
            return 0;
        }
        return 1;
    }


    /**
     * Sorts the deck by question
     */
    public void sort()
    {
        Collections.sort(deckOfCards, new Comparator<Flashcard>() {
            @Override
            public int compare(Flashcard o1, Flashcard o2) {
                return Collator.getInstance().compare(o1.getQuestion(), o2.getQuestion());
            }
        });
    }

}
