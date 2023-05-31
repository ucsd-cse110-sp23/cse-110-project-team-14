package sayit;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ParseVoiceTest {
    ParseVoice parseVoice = new ParseVoice();

    @BeforeEach
    void setUp(){

    }

    @Test
    void parsingIntentQuestion(){
        String string = "Question. What is 3+3";
        assertEquals(parseVoice.parseIntent(string), "Question");

    }

    @Test
    void parsingQueryQuestion(){
        String string = "Question. What is 3+3";
        assertEquals(parseVoice.parseQuery(string), "What is 3+3");

    }

    @Test
    void parsingIntentDelete(){
        String string = "Delete.";
        assertEquals(parseVoice.parseIntent(string), "Delete Prompt");

        String s2 = "Delete Prompt";
        assertEquals(parseVoice.parseIntent(s2), "Delete Prompt");
        

    }

    @Test
    void parsingIntentClearAll(){
        String string = "Clear All.";
        assertEquals(parseVoice.parseIntent(string), "Clear All");

    }

    
    
}
