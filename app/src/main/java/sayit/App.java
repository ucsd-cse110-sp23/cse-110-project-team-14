package sayit;

/**
 * Creates an instance of a frame object. Further documented in Frame.java
 */
public class App { // main class
    public static void main(String[] args) throws Exception {
        new LoginFrame();
        //new CreateAccountFrame();
        //new AutoLoginFrame();
        //new Frame(new Whisper(), new ChatGPT());
        new MyServer();       
    }
}