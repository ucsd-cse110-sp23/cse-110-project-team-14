import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Story3Test1 {
    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage();
    }

    @Test
    public void testDeleteQuestionWithoutSelection() {
        // Add some questions and answers to the storage
        storage.addQuestion("Question 1", "Answer 1");
        storage.addQuestion("Question 2", "Answer 2");

        // Get the initial size of the storage
        int initialSize = storage.questionAnswerMap.size();

        // Attempt to delete a question without selecting any
        storage.deleteQuestion("Non-existent Question");

        // Verify that the storage size remains the same
        assertEquals(initialSize, storage.questionAnswerMap.size());

        // Verify that the storage remains unchanged
        HashMap<String, String> expectedQuestionAnswerMap = new HashMap<>();
        expectedQuestionAnswerMap.put("Question 1", "Answer 1");
        expectedQuestionAnswerMap.put("Question 2", "Answer 2");
        assertEquals(expectedQuestionAnswerMap, storage.questionAnswerMap);
    }
}
