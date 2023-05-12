package sayit;

import java.util.ArrayList;

public class Storage {
    // ArrayLists to store the questions and answers
    private ArrayList<String> questions;
    private ArrayList<String> answers;

    public Storage() {
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

    public int getIndex(String question) {
        for (int i = 0; i < this.questions.size(); i++) {
            // Iterate through the questions ArrayList
            if (this.questions.get(i).equalsIgnoreCase(question)) {
                // If the current question matches the given question (ignoring case), return the corresponding answer
                return i;
            }
        }
        return -1;
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
}