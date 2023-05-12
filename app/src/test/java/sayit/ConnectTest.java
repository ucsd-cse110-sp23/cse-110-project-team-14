package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectTest {
    private IAudioConverter converter;
    private IChatBot chat;

    @BeforeEach
    void setUp() {
        converter = new MockWhisper();
        chat = new MockChat();
    }

    @Test
    void audioFound() {
        try {
            String question = converter.audioToString("ST1.wav");
            assertEquals("What is 1 + 1", question);
            assertEquals("1+1 = 2", chat.askQuestion(question));
        } catch (Exception e) {

        }
        
    }
    @Test
    void audioNotFound() {
        try {
            String question = converter.audioToString("ST2.wav");
            assertEquals("", question);
        } catch (Exception e) {

        }
    }
}
