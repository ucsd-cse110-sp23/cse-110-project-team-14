package sayit;

/**
 * Use connect function whenever sending something through both 
 * Whisper and ChatGPT,
 */
public class WhisperToChat {
    private static String FILE_PATH = "recording.wav";
    public static String connect(String fileName) {
        if (fileName == null) {
            fileName = FILE_PATH;
        }
        try {
            String query = Whisper.audioToString(fileName);
            if(query.equals("")) {
                System.out.println("Microphone didn't pick up any noise");
                return null;
            } else {
                return ChatGPT.askQuestion(query);
            }
        } catch (Exception e) {
            System.out.println("Exception occured");
            return null;
        }
    }
}