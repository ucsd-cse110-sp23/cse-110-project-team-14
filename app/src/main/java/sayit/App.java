package sayit;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class AppFrame extends JFrame {
    private JButton askButton; // Create Ask Button
    private boolean askStop = false; // Toggle for Button
    private AudioRecorder recorder; // Create Listening Device

    AppFrame() {
        setSize(300, 100); // Set AppFrame paremeters
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1, 3));

        recorder = new AudioRecorder(); // Initialize buttons and recorder

        askButton = new JButton("Ask Question");
        this.add(askButton);

        addListeners(); // Give click functionality
        revalidate(); // Reupdate the frame
    }

    public void addListeners() {
    askButton.addActionListener(new ActionListener() { // Start recording when Clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            if (askStop) {
                recorder.stopRecording();
                askButton.setText("Ask Question");
                askStop = false; // Turn Button into Ask
                Thread t = new Thread( // Use another thread to not lag UI
                    () -> {
                    try {
                        String question = Whisper.audioToString();
                        if (question.equals("")) { // Check Mic input
                            System.out.println("Microphone didn't pick up any noise");
                        } else { // Only answer if input received
                            String answer = ChatGPT.askQuestion(question);
                            System.out.println(answer);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error occured");
                    }
                }
                );
                t.start(); // Start the threaded operation
            } else {
                recorder.startRecording();
                askButton.setText("Stop Question");
                askStop = true; // Turn button into Stop
            }
        }
        }
    );
    }
}

public class App { // Main App Class
    public static void main(String[] args) throws Exception {
        new AppFrame(); // call AppFrame
    }
}