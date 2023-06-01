package sayit;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Manual Story test, Uses api calls
 * To run test, click run button
 * Test uses ST1.wav as audio input, which contains Darren saying what is 1 + 1
 * Expected output is 1 + 1 = 2 in the command line
 */
public class TestChatWhisper1 extends JFrame {

  private JButton startButton;
  private IAudioConverter converter;
  private IChatBot chat;

  public static void main(String[] args) {
    new TestChatWhisper1(new Whisper(), new ChatGPT());
  }

  public TestChatWhisper1(IAudioConverter converter, IChatBot chat) { 
    this.converter = converter;
    this.chat = chat;

    setTitle("Story Test 1");
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
            String question = converter.audioToString("app\\ST1.wav");
            if(question.equals("")) {
              throw new Exception("Test Failed");
            }
            System.out.println(chat.askQuestion(question));
          } catch (Exception ex) {
            System.out.println(ex.getMessage());
          }
        }
      }
    );
  }

  
}
