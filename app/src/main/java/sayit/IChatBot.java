package sayit;

import java.io.IOException;

public interface IChatBot {
    public String askQuestion(String query) throws IOException, InterruptedException;
}
