/**
 *
 */

import java.io.File;
import java.util.LinkedList;

public class FlashcardReviewer {
	private Deck deck;
    private LinkedList<Flashcard> cardDeck;
    private int time;
    private int goal;
    
    public FlashcardReviewer() {
    	deck = new Deck();
    	cardDeck = deck.getCards();
    	time = 0;
    	goal = -1;
    }
    
    public void setGoal(int goal) {
        this.goal = goal; 
    }
    
    public Flashcard getNextCard() {
        return cardDeck.get(time);
    }
    
    public void setAsViewed() {
    	Flashcard card = cardDeck.removeFirst();
    	card.setViewed(true);
    	cardDeck.add(card);
    }
    
    public void createCardDeck(File file) {
        cardDeck = deck.getCards(file);
    }
    
    public int getGoal() {
        return goal;
    }
    
    public int getTime() {
        return time;
    }
    
    public int getCardDeckSize() {
        return cardDeck.size();
    }
    
    public void advance() {
        time++;
    }
    
    public int read() {
        if (deck.getCards() == null) return -1;
        if (this.getCardDeckSize() == 0) return 0;
        return 1;
    }
    
    public boolean save() {
        deck.setCards(cardDeck);
        return deck.saveCards();
    }
    
    public void setCardDeck(LinkedList<Flashcard> cards) {
        cardDeck = cards;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FlashcardReviewer)) return false;
        FlashcardReviewer that = (FlashcardReviewer) o;
        if (this.getTime() != that.getTime() || 
                this.getGoal() != that.getGoal() ||
                this.getCardDeckSize() != that.getCardDeckSize())
            return false;
        for (int i = 0; i < this.getCardDeckSize(); i++)
            if (!this.cardDeck.get(i).equals(that.cardDeck.get(i)))
                return false;
        return true;
    }

}
