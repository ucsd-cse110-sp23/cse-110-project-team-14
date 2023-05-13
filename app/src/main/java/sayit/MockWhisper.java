package sayit;

import java.io.IOException;

public class MockWhisper implements IAudioConverter {
    public String audioToString() throws IOException {
        return audioToString("ST1.wav");
    }

    public String audioToString(String filename) throws IOException {
        if(filename.equals("ST1.wav")) {
            return "What is 1 + 1";
        } else if (filename.equals("ST2.wav")) {
            return "";
        } else {
            throw new IOException("Unrecognized file name");
        }
    }
}
