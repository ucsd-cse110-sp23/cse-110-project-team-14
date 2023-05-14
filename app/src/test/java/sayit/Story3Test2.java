import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Story3Test2 {
    private Story3Test2 storage;

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
        int selectedQuestionIndex = 0; // Select the first question

        // Get the selected question and answer
        String selectedQuestion = storage.questions.get(selectedQuestionIndex);
        String selectedAnswer = storage.answers.get(selectedQuestionIndex);

        // Delete the selected question
        storage.deleteQuestion(selectedQuestionIndex);

        // Verify that the selected question and answer are deleted
        assertFalse(storage.questions.contains(selectedQuestion));
        assertFalse(storage.answers.contains(selectedAnswer));

        // Verify that the other question and answer remain in the storage
        ArrayList<String> expectedQuestions = new ArrayList<>();
        expectedQuestions.add("Question 2");
        assertEquals(expectedQuestions, storage.questions);

        ArrayList<String> expectedAnswers = new ArrayList<>();
        expectedAnswers.add("Answer 2");
        assertEquals(expectedAnswers, storage.answers);
    }
}
