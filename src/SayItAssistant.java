import java.util.ArrayList;

public class SayItAssistant {
    // ArrayLists to store the questions and answers
    private ArrayList<String> questions;
    private ArrayList<String> answers;

    public SayItAssistant() {
        // Constructor to initialize the ArrayLists
        this.questions = new ArrayList<String>();
        this.answers = new ArrayList<String>();
    }

    public void addQuestion(String question, String answer) {
        // Method to add a new question and answer to the ArrayLists
        this.questions.add(question);
        this.answers.add(answer);
    }

    public void deleteQuestion(int index) {
        // Method to delete a question and its answer from the ArrayLists
        this.questions.remove(index);
        this.answers.remove(index);
    }

    public void clearAll() {
        // Method to clear all the questions and answers from the ArrayLists
        this.questions.clear();
        this.answers.clear();
    }

    public String getAnswer(String question) {
        // Method to get the answer for a given question
        for (int i = 0; i < this.questions.size(); i++) {
            // Iterate through the questions ArrayList
            if (this.questions.get(i).equalsIgnoreCase(question)) {
                // If the current question matches the given question (ignoring case), return the corresponding answer
                return this.answers.get(i);
            }
        }
        // If no match was found, return a default message
        return "Sorry, I don't have an answer for that.";
    }

    public static void main(String[] args) {
        // Create a new instance of the SayItAssistant class
        SayItAssistant assistant = new SayItAssistant();
        
        // Add a new question and answer
        assistant.addQuestion("How did Louis Braille come up with the idea for braille?", "Louis Braille was inspired to create a tactile reading system after encountering a military code used by the French army.");
        
        // Get the answer for a specific question
        String answer = assistant.getAnswer("How did Louis Braille come up with the idea for braille?");
        System.out.println(answer);
        
        // Delete a question and its answer
        assistant.deleteQuestion(0); // deletes the first question and answer in the list
        
        // Clear all the questions and answers
        assistant.clearAll();
    }
}
