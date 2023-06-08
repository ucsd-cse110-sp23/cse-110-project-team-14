package sayit;

/**
 * Creates an instance of a frame object. Further documented in Frame.java
 */
public class App { // main class
    public static void main(String[] args) throws Exception {
        AccountUIToServer connecter = new AccountUIToServer(new Login(), new CreateAccount());
        new MyServer(connecter, new ServerToDB()); 
        new LoginFrame(connecter);
      
    }
}