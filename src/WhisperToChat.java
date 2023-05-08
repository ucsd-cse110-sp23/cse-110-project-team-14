/**
 * Use connect function whenever sending something through both 
 * Whisper and ChatGPT,
 */
public class WhisperToChat {
    
    private static String FILE_PATH = "recording.wav";
    
    public static String connect(String fileName) {
        try {
            String temp = Whisper.audioToString(fileName);
            if(temp.equals("")) {
                System.out.println("Microphone didn't pick up any noise");
                return null;
            } else {
                return ChatGPT.askQuestion(temp);
            }
        } catch (Exception e) {
            System.out.println("Exception occured");
        }
        return null;
    }

    public static String connect() {
        return connect(FILE_PATH);
    }
}
