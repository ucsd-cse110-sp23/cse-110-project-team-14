package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
    ICreate accCreate;
    ILogin accLogin;
    AccountUIToServer logic;

    @BeforeEach
    void setUp() {
       accLogin = new MockLogin();
       accCreate = new MockCreateAccount();
       logic = new AccountUIToServer(accLogin, accCreate);
    }

    @Test
    void loginTest() {
        assertEquals("true", logic.login("dwng@ucsd.edu", "abcd1234"));
        assertEquals("Incorrect password", logic.login("dwng@ucsd.edu", "1234"));
        assertEquals("No account with that username", logic.login("test@ucsd.edu", "abcd1234"));
    }

    @Test
    void createTest() {
        assertEquals("Created Account", logic.createAccount("test@ucsd.edu", "abcd1234","abcd1234"));
        assertEquals("username can not contain commas", logic.createAccount("ab,cd@ucsd.edu", "1234","1234"));
        assertEquals("passwords do not match", logic.createAccount("dwng@ucsd.edu", "abcd1234","dwng@ucsd.edu"));
        assertEquals("Username taken", logic.createAccount("dwng@ucsd.edu", "abcd1234","abcd1234"));
    }
}
