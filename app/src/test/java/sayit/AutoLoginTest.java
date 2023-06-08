package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoLoginTest {
    ICreate accCreate;
    ILogin accLogin;
    AccountUIToServer logic;
    ReadLoginInformation readLoginInformation;
    ExportLoginInformation exportLoginInformation;

    @BeforeEach
    void setUp() {
       accLogin = new MockLogin();
       accCreate = new MockCreateAccount();
       logic = new AccountUIToServer(accLogin, accCreate);
       readLoginInformation = new ReadLoginInformation();
       exportLoginInformation = new ExportLoginInformation();

       exportLoginInformation.exportLogin("dwng@ucsd.edu", "abcd1234", true);
    }

    @Test
    void loginTest() {
        assertEquals(true,readLoginInformation.autoLoginEnabled());
        assertEquals("true", logic.login(readLoginInformation.readUsername(), 
        readLoginInformation.readPassword()));
    }

    @Test
    void createTest() {
        assertEquals("Created Account", logic.createAccount("test@ucsd.edu", "abcd1234","abcd1234"));
        exportLoginInformation.exportLogin("test@ucsd.edu", "abcd1234", true);
        assertEquals(true,readLoginInformation.autoLoginEnabled());
        assertEquals("true", logic.login(readLoginInformation.readUsername(), 
        readLoginInformation.readPassword()));
    }
}
