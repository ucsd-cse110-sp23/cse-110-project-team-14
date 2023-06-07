package sayit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DatabaseTest {


    @Test void tests() { 
        MockDatabase database = new MockDatabase();
        database.addCommand("What is 1 + 1", "2", "abcd");
        assertEquals("2", database.getStorage().get("What is 1 + 1"));
        database.addCommand("Who am I", "Darren", "abcd");
        assertEquals("Darren", database.getStorage().get("Who am I"));
        database.deleteCommand("Who am I", "abcd");
        assertEquals(null, database.getStorage().get("Who am I"));
        database.clearAll("abcd");
        assertEquals(null, database.getStorage().get("What is 1 + 1"));
        assertEquals(0, database.getStorage().size());
    }

}
