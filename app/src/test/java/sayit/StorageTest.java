package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StorageTest {
    @Test void addQuestion() {
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");
        
        assertEquals("Paris", store.getAnswer("What is the capital of France?"));
    }

    @Test void deleteQuestion() {
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");
        store.addQuestion("What is the capital of Italy?", "Rome");
        store.deleteQuestion(1);
        
        assertEquals("Paris", store.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of Italy?"));
    }

    @Test void clearAll() {
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");
        store.addQuestion("What is the capital of Italy?", "Rome");
        store.clearAll();

        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of Italy?"));
    }

    @Test void getAnswer() {
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");

        assertEquals("Paris", store.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of Italy?"));
    }
}
