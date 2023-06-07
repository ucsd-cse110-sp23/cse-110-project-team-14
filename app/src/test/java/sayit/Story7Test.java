package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Story7Test {
    private Login login;
    private AccountUIToServer connecter;
    private MyServer myServer;
    private LoginFrame loginFrame;

    @BeforeEach
    void setUp() {
        login = new Login();
        AccountUIToServer connecter = new AccountUIToServer(new Login(), new CreateAccount());
        loginFrame = new LoginFrame(connecter);
        try{myServer = new MyServer(connecter, new ServerToDB());} 
        catch(Exception e){};
    }


    @Test
    void loginValid(){
        String loginString = login.login("Apollo", "123");
        assertEquals("true", loginString);
    }

    @Test
    void loginInvalidPassword(){
        String loginString = login.login("Apollo", "1234");
        assertEquals("Incorrect password", loginString);
    }

    @Test void loginInvalidUser(){
        String loginString = login.login("Apollo2","123");
        assertEquals("No account with that username",loginString);
    }

}