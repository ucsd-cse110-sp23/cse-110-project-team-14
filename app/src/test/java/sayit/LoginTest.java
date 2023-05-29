package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    //TODO needs mocking
    @BeforeEach
    void setUp() {
        try {
            MyServer server = new MyServer();
        } catch (Exception e) {
            
        }
    }

    @Test
    void correctLogin() {
        
    }
}
