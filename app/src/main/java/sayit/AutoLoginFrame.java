
package sayit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;


public class AutoLoginFrame extends JFrame {

    AutoLoginFrame myAutoLoginFrame;
    LoginFrame myLoginFrame;

    private JLabel questionLabel;
    private JButton yesButton;
    private JButton noButton;

    /*
     * Method for setting the location of the button in the footer area of the 
     * app
     */
    private void setElements(SpringLayout myLayout, JLabel  questionLabel,
        JButton yesButton,  JButton noButton) {
        
        this.add(questionLabel);
        this.add(yesButton);
        this.add(noButton);

        myLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, questionLabel, 250, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, questionLabel, 100, SpringLayout.NORTH, this);

        myLayout.putConstraint(SpringLayout.WEST, yesButton, 25, SpringLayout.WEST, questionLabel);
        myLayout.putConstraint(SpringLayout.NORTH, yesButton, 50, SpringLayout.NORTH, questionLabel);

        myLayout.putConstraint(SpringLayout.WEST, noButton, 150, SpringLayout.WEST, questionLabel);
        myLayout.putConstraint(SpringLayout.NORTH, noButton, 50, SpringLayout.NORTH, questionLabel);

    }

    AutoLoginFrame () {
        myAutoLoginFrame = this;

        this.setSize(500, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set location of frame in the center of the window
        this.setLocationRelativeTo(null);

        SpringLayout myLayout = new SpringLayout();
        this.setLayout(myLayout);

        questionLabel = new JLabel("Do you want to automatically login next time?");

        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        setElements(myLayout, questionLabel, yesButton, noButton);

        addListeners();
        revalidate();
    }

    public void addListeners() {
        yesButton.addActionListener(new ActionListener() { // start recording on click
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yes button pressed!");
                new Frame(new Whisper(), new ChatGPT());
                myAutoLoginFrame.dispose();
            }
        });

        noButton.addActionListener(new ActionListener() { // start recording on click
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("No button pressed!");
                new Frame(new Whisper(), new ChatGPT());
                myAutoLoginFrame.dispose();
            }
        });
        
    }
}

