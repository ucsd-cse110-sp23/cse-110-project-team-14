package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    
    @BeforeEach
    void setUp() {
        try {
            MyServer server = new MyServer();
        } catch (Exception e) {

        }
        
    }

    @Test
    void correctLogin() {
        assertEquals("true", Login.login("test@test.com", "abcd1234"));
    }
}
