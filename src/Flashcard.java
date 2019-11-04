/**
 * Stores and edits information for the questions and answers
 */
public class Flashcard {

    /**
     * Constructor
     * @param question
     * @param answer
     */
    public Flashcard(string question, string answer){
        this.question = question;
        this.answer = answer;
    }

    public string getQuestion()
    {
        return question;
    }

    

    public string getAnswer()
    {
        return answer;
    }

    public void setQuestion(string newQuestion)
    {
        question = newQuestion;
    }

    public void setAnswer(string newAnswer)
    {
        answer = newAnswer;
    }


    private string question;
    private string answer;



}
