/**
 * Constructs the Flashcard object with question and answer information
 */
public class Flashcard {

    /**
     * Constructor
     * @param question the question for the card
     * @param answer the answer for the card
     */
    public Flashcard(string question, string answer){
        this.question = question;
        this.answer = answer;
    }

    /**
     * Gets the question
     * @return Question the question on the card
     */
    public string getQuestion()
    {
        return question;
    }


    /**
     * Gets the Answer
     * @return answer the answer on the card
     */
    public string getAnswer()
    {

        return answer;
    }

    /**
     * Sets the Question
     * @param newQuestion the new question for the card
     */
    public void setQuestion(string newQuestion)
    {
        question = newQuestion;
    }

    /**
     * Sets the answer
     * @param newAnswer the new answer for the card
     */
    public void setAnswer(string newAnswer)
    {
        answer = newAnswer;
    }


    private string question;
    private string answer;



}
