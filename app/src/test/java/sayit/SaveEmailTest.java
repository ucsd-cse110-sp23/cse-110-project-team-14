package sayit;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SaveEmailTest {
    @Test
    void parseTest() {
        EmailConnecter eCon = new EmailConnecter();
        eCon.updateVal("Document{{First=Darren, Last=Ng, Display=dwng, Email=dwng@ucsd.edu, SMTP=abcd, TLS=efg, password=abcd1234}}");
        assertEquals(eCon.getFirst(), "Darren");
        assertEquals(eCon.getLast(), "Ng");
        assertEquals(eCon.getDisplay(), "dwng");
        assertEquals(eCon.getEmail(), "dwng@ucsd.edu");
        assertEquals(eCon.getSMTP(), "abcd");
        assertEquals(eCon.getTLS(), "efg");
        assertEquals(eCon.getPassword(), "abcd1234");
    }
}
