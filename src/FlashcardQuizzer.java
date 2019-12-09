import java.util.LinkedList;

/**
 * A Deck of Cards is presented as a quiz
 */
public class FlashcardQuizzer {
//deck object has flashcard object that is a linked list.
	// fields to test if the user answered correctly or not
	private int totalQuestions, totalCorrect;
	private Deck currentDeck = null;

	public FlashcardQuizzer(Deck currentDeck) {
		this.currentDeck = currentDeck;
		totalQuestions = currentDeck.getQuestionCount();
		totalCorrect = 0;
	}

	public boolean testQuestion(String currentQuestion,String currentAnswer) {
		if(currentQuestion.compareTo(currentAnswer) == 0) {
			return true;
		}
		return false;
	}

	public int getCorrectCount(String[] selectedChoices) {
		LinkedList<Flashcard> cards = currentDeck.getCards();
		int i = 0;
		boolean result = false;
		while(i < cards.size()) {
			String currentQuestion = cards.get(i).getQuestion();
			String currentAnswer = selectedChoices[i];
			result = testQuestion(currentQuestion,currentAnswer);
			if(result) {
				totalCorrect++;
			}
			i++;
		}
		return 0;
	}
	public int getTotalCorrect() {
		return totalCorrect;
	}
	@Override
	public String toString() {
		return "Total questions are " + currentDeck.getQuestionCount() + ".Number answered correctly is/are: " + totalCorrect;
	}
	
	public static void main(String[] args) {

	}

}
