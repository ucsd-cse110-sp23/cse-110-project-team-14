package sayit;

/**
 * Creates an instance of a frame object. Further documented in Frame.java
 */
public class App { // main class
    public static void main(String[] args) throws Exception {
        new MyServer(new AccountUIToServer(new Login(), new CreateAccount())); 
        new Frame(new Whisper(), new ChatGPT());
    }
}