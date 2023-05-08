import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Manual Story test, Uses api calls
 */
public class Story1Test1 extends JFrame {

  private JButton startButton;

  public static void main(String[] args) {
    new Story1Test1();
  }

  public Story1Test1() {
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
            String temp = Whisper.audioToString("ST1.wav");
            System.out.println(ChatGPT.askQuestion(temp));
          } catch (Exception ex) {
            System.out.println("Error occured");
          }
        }
      }
    );
  }

  
}
