package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ConnectTest {
    @Test void audioFound() {
        assertEquals("What is 1 plus 1?", WhisperToChat.connect("ST1.wav"));
    }

    @Test void audioNotFound() {
        assertEquals(null, WhisperToChat.connect("ST2.wav"));
    }
}