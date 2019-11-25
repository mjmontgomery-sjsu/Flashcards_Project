/**
 * Constructs the Flashcard object with question and answer information
 */
public class Flashcard {

    /**
     * Constructor
     * @param question the question for the card
     * @param answer the answer for the card
     */
    public Flashcard(String question, String answer, boolean beenViewed){
        this.question = question;
        this.answer = answer;
        this.beenViewed = beenViewed;
    }

    /**
     * Gets the question
     * @return Question the question on the card
     */
    public String getQuestion()
    {
        return question;
    }


    /**
     * Gets the Answer
     * @return answer the answer on the card
     */
    public String getAnswer()
    {

        return answer;
    }

    /**
     * Gets the status of whether the card has been viewed by the user
     * @return beenViewed a boolean of wjether the card has been viewed
     */
    public boolean getView()
    {
        return beenViewed;
    }

    /**
     * Sets the Question
     * @param newQuestion the new question for the card
     */
    public void setQuestion(String newQuestion)
    {
        question = newQuestion;
    }

    /**
     * Sets the answer
     * @param newAnswer the new answer for the card
     */
    public void setAnswer(String newAnswer)
    {
        answer = newAnswer;
    }

    /**
     * Sets the status of whether the card has been viewed
     * @param view
     */
    public void setViewed(boolean view)
    {
        beenViewed = view;
    }

    private String question;
    private String answer;
    private boolean beenViewed;



}
