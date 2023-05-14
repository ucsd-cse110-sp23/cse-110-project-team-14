package sayit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Story3Test2 {
    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage();
    }

    @Test
    public void testDeleteQuestionWithSelection() {
        // Add some questions and answers to the storage
        storage.addQuestion("Question 1", "Answer 1");
        storage.addQuestion("Question 2", "Answer 2");

        // Select a question to delete
        String selectedQuestion = "Question 1";

        // Delete the selected question
        storage.deleteQuestion(selectedQuestion);

        // Verify that the selected question and answer are deleted
        assertFalse(storage.containsQuestion(selectedQuestion));

        // Verify that the other question and answer remain in the storage
        HashMap<String, String> expectedQuestionAnswerMap = new HashMap<>();
        expectedQuestionAnswerMap.put("Question 2", "Answer 2");
        assertEquals(expectedQuestionAnswerMap, storage.getHashMap());
    }
}
