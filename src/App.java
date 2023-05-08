import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class AppFrame extends JFrame {
    private JButton askButton; // Create Ask Button
    private JButton stopButton; // Create Stop Button
    private AudioRecorder recorder; // Create Listening Device

    AppFrame() {
        setSize(300, 100); // Set Appframe parameters
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1, 3));

        recorder = new AudioRecorder(); // Initialize buttons and recorder

        askButton = new JButton("Ask Question");
        this.add(askButton);

        stopButton = new JButton("Stop Question");
        this.add(stopButton);

        addListeners(); // Give click functionality
        revalidate();
    }

    public void addListeners() {
    askButton.addActionListener(new ActionListener() { // start recording on click
        @Override
        public void actionPerformed(ActionEvent e) {
            recorder.startRecording();
        }
        }
    );
    stopButton.addActionListener(new ActionListener() { // stop recording and save the answer as string
        @Override
        public void actionPerformed(ActionEvent e) {
            recorder.stopRecording();
            Thread t = new Thread(
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
            t.start();
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