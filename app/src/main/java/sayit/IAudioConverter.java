package sayit;

import java.io.IOException;

public interface IAudioConverter {
    public String audioToString() throws IOException;
    public String audioToString(String filename) throws IOException;
}
