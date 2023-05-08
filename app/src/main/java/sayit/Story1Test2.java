package sayit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Manual Story test, Uses api calls
 * Test uses ST1.wav as audio input, which contains 3 seconds of no speaking
 * Should print out "Microphone didn't pick up any noise" and then "Test passed"
 */
public class Story1Test2 extends JFrame {

  private JButton startButton;

  public static void main(String[] args) {
    new Story1Test2();
  }

  public Story1Test2() {
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
            String answer = WhisperToChat.connect("ST2.wav");
            if(answer == null) System.out.println("Test passed");
          } catch (Exception ex) {
            System.out.println("Error occured");
          }
        }
      }
    );
  }

  
}
