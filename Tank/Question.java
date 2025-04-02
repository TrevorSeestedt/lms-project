package Tank;

import java.util.ArrayList;

public class Question {
    protected String prompt;
    protected int correctAnswer;
    protected int numOfChoices;
    protected ArrayList<String> choices;

    public Question() {
        this.choices = new ArrayList<String>();
    }

    /**
    * Sets prompt, correctAnswer, choice, and numOfChoices.
    * @param prompt The question the user will be asked to answer.
    * @param correctAnswer The answer that would be considered right.
    * @param choices The choices the user will be presented to choose for their answer.
    * @param numOfChoices The amount of choices the user will be given to choose from.
    */
    public Question(String prompt, int correctAnswer, ArrayList<String> choices, int numOfChoices) {
        setPrompt(prompt);
        setCorrectAnswer(correctAnswer);
        setChoices(choices);
        setNumOfChoices(numOfChoices);
    }

    /**
    * Gets the prompt for this question.
    * @return The string prompt.
    */
    public String getPrompt() {
        return this.prompt;
    }

    /**
    * Sets the prompt for this question.
    * @param prompt The question the user will be asked to answer.
    */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
    * Gets the correct answer for this question.
    * @return the int for the correct answer for this question.
    */
    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
    * Sets the correct answer for this question.
    * @param correctAnswer The answer that would be considered right.
    */
    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
    * Gets the number of choices for this question.
    * @return the int for the number of choices the user will have for this question.
    */
    public int getNumOfChoices() {
        return this.numOfChoices;
    }

    /**
    * Sets the number of choices for this question.
    * @param numOfChoices the number of choices the user will have for this question.
    */
    public void setNumOfChoices(int numOfChoices) {
        this.numOfChoices = numOfChoices;
    }

    /**
    * Gets the choices for this question
    * @return A list of the choices the user will have for this question.
    */
    public ArrayList<String> getChoices() {
        return this.choices;
    }

    /*public void addChoice(String choice) {
        if(getNumOfChoices() > getChoices().size())
            this.choices.add(choice);
    }*/

    /**
    * Checks if the size of the choices array is large enough to hold the number of choices.
    * If not, the array size is increased until the number of choices and the size of the array are even.
    * @param choices The array where the user's choices for answering this question are stored.
    */
    //error
    public void addChoices(ArrayList<String> choices) {
        if(getNumOfChoices() >= choices.size()) {
            for(int i=0;i<choices.size();++i) {
                this.choices.add(choices.get(i));
            }
        }
    }

    /**
    * Sets the choices for this question.
    * @param choices The array list where the user's choices for answering this question are stored.
    */
    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    /**
    * Gives the user encouraging praise.
    * @return A string to praise the user.
    */
    public String praiseUser() {
        return "great job!!!";
    }

    /**
    * A string to represent this question.
    * @return A string to print out the prompt and choices.
    */
    public String toString() {
        return "Prompt: " + this.getPrompt() + "\n" +this.choicesToString();
    }

    /**
    * loops until the variable i is equal to the number of choices, 
    * then prints the lines of choices based on the value of the variable.
    * @return The string of choices the user will have for this question.
    */
    public String choicesToString() {
        String choicesString = "";
        for(int i=0;i<numOfChoices;++i) {
            choicesString += i + ". " + getChoices().get(i) + "\n";
        }

        return choicesString;
    }
}