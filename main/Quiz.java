package Tank;

import java.util.ArrayList;

public class Quiz extends Question {
    private ArrayList<Question> questions;
    
    public Quiz() {
        
    }
    /**
    * Loops through the questions and adds them for each index of the array.
    * @param questions list of the questions that are in the quiz.
    */
    public Quiz(ArrayList<Question> questions) {
        ArrayList<Question> tempQArray = new ArrayList<>();
        for(int i=0;i<questions.size();++i) {
            //tempQArray.add(createQuestion(prompt, correctAnswer, choices, numOfChoices)); 
            tempQArray.add(questions.get(i));
        }
        setQuestion(tempQArray);;
    }

    /**
    * Allows the creation of a new question for a quiz.
    * @param prompt String asking the user the question that they are supposed to answer.
    * @param correctAnswer int value of the correct answer to the quiz question.
    * @param choices Array of possible answer choices a user has for a question.
    * @param numOfChoices int value for the amount of choices a user will have on the question.
    * @return temporary variable where question to be created is stored.
    */
    public Question createQuestion(String prompt, int correctAnswer, ArrayList<String> choices, int numOfChoices) {
        Question tempQuestion = new Question();

        tempQuestion.setPrompt(prompt);
        tempQuestion.setCorrectAnswer(correctAnswer);
        tempQuestion.addChoices(choices); 
        tempQuestion.setNumOfChoices(numOfChoices);

        return tempQuestion;
    }

    /**
    * Allows the program to access the array of questions.
    * @return accesses the list of questions in the array.
    */
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    /**
    * Allows the program to make changes to questions.
    * @param questions list of the questions in the quiz.
    */
    public void setQuestion(ArrayList<Question> questions) {
        this.questions = questions;
    }
    
    //question toString
    public ArrayList<String> questionStrings() {
        ArrayList<String> tempQuestions = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            tempQuestions.add(questions.get(i).toString());
        }
        return tempQuestions;
    }

}
