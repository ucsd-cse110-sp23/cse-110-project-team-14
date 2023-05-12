package sayit;

import java.io.IOException;

public class MockChat implements IChatBot{

    
    public String askQuestion(String query) throws IOException, InterruptedException {
        if(query.equals("What is 1 + 1")) {
            return "1+1 = 2";
        } else {
            throw new IOException("Unrecognized query");
        }
    }
    
}
