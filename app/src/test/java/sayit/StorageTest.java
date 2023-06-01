package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StorageTest {
    @Test void addQuestion() { // Test adding question to storage
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");
        
        assertEquals("Paris", store.getAnswer("What is the capital of France?"));
    }

    @Test void deleteQuestion() { // Test deleting question from storage
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");
        store.addQuestion("What is the capital of Italy?", "Rome");
        store.deleteQuestion("What is the capital of Italy?");
        
        assertEquals("Paris", store.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of Italy?"));
    }

    @Test void clearAll() { // Test clearing all questions from storage
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");
        store.addQuestion("What is the capital of Italy?", "Rome");
        store.clearAll();

        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of Italy?"));
    }

    @Test void getAnswer() { // Test answer retrieval from storage
        Storage store = new Storage();
        store.addQuestion("What is the capital of France?", "Paris");

        assertEquals("Paris", store.getAnswer("What is the capital of France?"));
        assertEquals("Sorry, I don't have an answer for that.", store.getAnswer("What is the capital of Italy?"));
    }
}
