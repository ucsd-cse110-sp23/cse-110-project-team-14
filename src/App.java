import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class AppFrame extends JFrame {
    private JButton askButton; // Create Ask Button
    private boolean askStop = false; // boolean toggle
    private AudioRecorder recorder; // Create Listening Device

    AppFrame() {
        setSize(300, 100); // Set Appframe parameters
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
    askButton.addActionListener(new ActionListener() { // start recording on click
        @Override
        public void actionPerformed(ActionEvent e) {
            if (askStop) {
                recorder.stopRecording();
                askButton.setText("Ask Question"); //change button text
                askStop = false; // change toggle
                Thread t = new Thread( // use another thread for answer computation to not lag UI
                    () -> {
                    try {
                        String question = Whisper.audioToString();
                        String answer = ChatGPT.askQuestion(question);
                        System.out.println(answer);
                    } catch (Exception ex) {
                        System.out.println("Error occured");
                    }
                }
                );
                t.start(); // start the new thread
            } else {
                recorder.startRecording();
                askButton.setText("Stop Question"); // change text back
                askStop = true; // change toggle back
            }
        }
        }
    );
    }
}

public class App { // main class
    public static void main(String[] args) throws Exception {
        new AppFrame(); // call AppFrame
    }
}