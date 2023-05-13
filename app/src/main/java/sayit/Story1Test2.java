package sayit;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Manual Story test, Uses api calls
 * Test uses ST1.wav as audio input, which contains 3 seconds of no speaking
 * Should print out "Microphone didn't pick up any sound" and then "Test passed"
 */
public class Story1Test2 extends JFrame {

  private JButton startButton;
  IAudioConverter converter;
  IChatBot chat;

  public static void main(String[] args) {
    new Story1Test2(new Whisper(), new ChatGPT());
  }

  public Story1Test2(IAudioConverter converter, IChatBot chat) {
    this.converter = converter;
    this.chat = chat;
    setTitle("Story Test 2");
    setLayout(new GridLayout(1, 3));

    startButton = new JButton("Run");
    this.add(startButton);


    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300, 100);
    setVisible(true);

    addListeners();
  }

  public void addListeners() {
    startButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            String question = converter.audioToString("app\\ST2.wav");
            if(!question.equals("")) {
              throw new Exception("Test Failed");
            }
            System.out.println("Microphone didn't pick up any sound");
            System.out.println("Test Passed");
          } catch (Exception ex) {
            System.out.println(ex.getMessage());
          }
        }
      }
    );
  }

  
}
