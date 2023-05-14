import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Story3Test3 {
    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage();
    }

    @Test
    public void testDeleteQuestionWhileBeingAsked() {
        // Add some questions and answers to the storage
        storage.addQuestion("Question 1", "Answer 1");
        storage.addQuestion("Question 2", "Answer 2");

        // Start asking a question
        String questionBeingAsked = "Question 1";

        // Get the initial state of the storage
        HashMap<String, String> initialQuestionAnswerMap = new HashMap<>(storage.questionAnswerMap);

        // Attempt to delete a question while it is being asked
        storage.deleteQuestion(questionBeingAsked);

        // Verify that no
