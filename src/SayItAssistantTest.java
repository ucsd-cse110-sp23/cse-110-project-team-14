import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SayItAssistantTest {
    private SayItAssistant assistant;

    @BeforeEach
    void setUp() {
        assistant = new SayItAssistant();
    }

    @Test
    void addQuestion() {
        assistant.addQuestion("What is the capital of France?", "Paris");
        assertEquals("Paris", assistant.getAnswer("What is the capital of France?"));
    }

    @Test
    void deleteQuestion() {
        assistant.addQuestion("What is the capital of France?", "Paris");
        assistant.addQuestion("What is the capital of Italy?", "Rome");

        assistant.deleteQuestion(1);

        assertEquals("Paris", assistant.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", assistant.getAnswer("What is the capital of Italy?"));
    }

    @Test
    void clearAll() {
        assistant.addQuestion("What is the capital of France?", "Paris");
        assistant.addQuestion("What is the capital of Italy?", "Rome");

        assistant.clearAll();

        assertEquals("Sorry, I don't have an answer for that.", assistant.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", assistant.getAnswer("What is the capital of Italy?"));
    }

    @Test
    void getAnswer() {
        assistant.addQuestion("What is the capital of France?", "Paris");

        assertEquals("Paris", assistant.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", assistant.getAnswer("What is the capital of Italy?"));
    }
}
