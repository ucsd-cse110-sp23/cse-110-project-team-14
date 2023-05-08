package sayit;

/**
 * Use connect function whenever sending something through both 
 * Whisper and ChatGPT,
 */
public class WhisperToChat {
    public static String connect(String fileName) {
        if (fileName == null) {
            fileName = "recording.wav";
        }
        try {
            String query = Whisper.audioToString(fileName);
            if(query.equals("")) {
                System.out.println("Microphone didn't pick up any noise");
                return null;
            } else {
                System.out.println("WE FOUND IT");
                return ChatGPT.askQuestion(query);
            }
        } catch (Exception e) {
            System.out.println("Exception occured");
            return null;
        }
    }
}