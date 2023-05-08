package sayit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Manual Story test, Uses api calls
 * To run test, click run button
 * Test uses ST1.wav as audio input, which contains Darren saying what is 1 + 1
 * Expected output is 1 + 1 = 2 in the command line
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
            String answer = WhisperToChat.connect("ST1.wav");
            if(answer == null) System.out.println(answer);
          } catch (Exception ex) {
            System.out.println("Error occured");
          }
        }
      }
    );
  }

  
}
