package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void audioFound() { // Test audio received and converted to string
        try {
            String question = converter.audioToString("ST1.wav");
            assertEquals("What is 1 + 1", question);
            assertEquals("1+1 = 2", chat.askQuestion(question));
        } catch (Exception e) {

        }
        
    }
    @Test
    void audioNotFound() { // Test audio not received
        try {
            String question = converter.audioToString("ST2.wav");
            assertEquals("", question);
        } catch (Exception e) {

        }
    }
}
