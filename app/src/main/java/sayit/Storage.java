package sayit;

import java.util.HashMap;

/**
 * The Storage class contains methods to add, delete, clear, and retrieve
 * question-answer pairs stored in a HashMap.
 */
public class Storage {
    private HashMap<String, String> questionAnswerMap;

    public Storage() {
        questionAnswerMap = new HashMap<String, String>();
    }

    public void addQuestion(String question, String answer) {
        questionAnswerMap.put(question, answer);
    }

    public void deleteQuestion(String string) {
        questionAnswerMap.remove(string);
    }

    public void clearAll() {
        questionAnswerMap.clear();
    }

    public String getAnswer(String question) {
        if (questionAnswerMap.containsKey(question)) {
            return questionAnswerMap.get(question);
        } else
            return "Sorry, I don't have an answer for that.";
    }
}