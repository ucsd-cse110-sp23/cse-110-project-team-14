package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ConnectTest {
    @Test void audioFound() {
        assertNotEquals(null, WhisperToChat.connect("ST1.wav"));
    }

    @Test void audioNotFound() {
        assertEquals(null, WhisperToChat.connect("ST2.wav"));
    }
}