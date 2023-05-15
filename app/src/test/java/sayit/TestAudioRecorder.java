package sayit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestAudioRecorder extends JFrame {

  private JButton startButton;
  private JButton stopButton;
  private JLabel recordingLabel;
  private AudioRecorder audioRecorder;

  private IAudioConverter converter; 
  private IChatBot chat;

  public static void main(String[] args) {
    new TestAudioRecorder(new Whisper(), new ChatGPT());
  }

  public TestAudioRecorder(IAudioConverter converter, IChatBot chat) { // Test audio recorder functionality (MANUAL)
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
            String question = converter.audioToString("app\\recording.wav");
            if(question.equals("")) {
              System.out.println("Microphone didn't pick up any sound");
            } else {
              System.out.println(chat.askQuestion(question));
            }
          } catch (Exception ex) {
            System.out.println(ex.getMessage());
          }
        }
      }
    );
  }

  
}