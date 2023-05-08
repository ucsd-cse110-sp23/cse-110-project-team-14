import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestAudioRecorder extends JFrame {

  private JButton startButton;
  private JButton stopButton;
  private JLabel recordingLabel;
  private AudioRecorder audioRecorder;

  public static void main(String[] args) {
    new TestAudioRecorder();
  }

  public TestAudioRecorder() {
    setTitle("Audio Recorder");
    setLayout(new GridLayout(1, 3));

    startButton = new JButton("Start");
    this.add(startButton);

    stopButton = new JButton("Stop");
    this.add(stopButton);

    recordingLabel = new JLabel("Recording");
    recordingLabel.setForeground(Color.RED);
    recordingLabel.setPreferredSize(new Dimension(20, 20));
    recordingLabel.setVisible(false);
    this.add(recordingLabel);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300, 100);
    setVisible(true);
    
    audioRecorder = new AudioRecorder();
    addListeners();
  }

  public void addListeners() {
    startButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          audioRecorder.startRecording();
          recordingLabel.setVisible(true);
        }
      }
    );
    stopButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          audioRecorder.stopRecording();
          recordingLabel.setVisible(false);
          try {
            String answer = WhisperToChat.connect();
            if(!answer.equals(null)) System.out.println(answer);
          } catch (Exception ex) {
            System.out.println("Error occured");
          }
        }
      }
    );
  }

  
}
