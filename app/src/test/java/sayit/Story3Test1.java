import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Story3Test1 {
    private Story3Test1 storage;

    @BeforeEach
    public void setup() {
        storage = new Story3Test1();
    }

    @Test
    public void testDeleteQuestionWithoutSelection() {
        // Add some questions and answers to the storage
        storage.addQuestion("Question 1", "Answer 1");
        storage.addQuestion("Question 2", "Answer 2");

        // Get the initial size of the storage
        int initialSize = storage.questions.size();

        // Attempt to delete a question without selecting any
        storage.deleteQuestion(-1);

        // Verify that the storage size remains the same
        assertEquals(initialSize, storage.questions.size());
        assertEquals(initialSize, storage.answers.size());

        // Verify that the storage remains unchanged
        ArrayList<String> expectedQuestions = new ArrayList<>();
        expectedQuestions.add("Question 1");
        expectedQuestions.add("Question 2");
        assertEquals(expectedQuestions, storage.questions);

        ArrayList<String> expectedAnswers = new ArrayList<>();
        expectedAnswers.add("Answer 1");
        expectedAnswers.add("Answer 2");
        assertEquals(expectedAnswers, storage.answers);
    }
}