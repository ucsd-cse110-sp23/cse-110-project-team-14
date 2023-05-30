
package sayit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import javax.swing.WindowConstants;


public class CreateAccountFrame extends JFrame {

    CreateAccountFrame myCreateAccountFrame;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel errorLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField confirmPasswordTextField;

    private JButton createAccountButton;

/*
     * Method for setting the location of the button in the footer area of the 
     * app
     */
    private void setElements(SpringLayout myLayout, JLabel usernameLabel, 
        JLabel passwordLabel, JLabel confirmPasswordLabel, JTextField usernameTextField, 
        JTextField passwordTextField, JTextField confirmPasswordTextfield, 
        JButton createAccountButton, JLabel errorLabel) {
        
        this.add(usernameLabel);
        this.add(usernameTextField);
        this.add(passwordLabel);
        this.add(passwordTextField);
        this.add(confirmPasswordLabel);
        this.add(confirmPasswordTextfield);
        this.add(createAccountButton);
        this.add(errorLabel);

        myLayout.putConstraint(SpringLayout.WEST, usernameLabel, 250, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 100, SpringLayout.NORTH, this);
        myLayout.putConstraint(SpringLayout.WEST, usernameTextField, 50, SpringLayout.EAST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 0, SpringLayout.NORTH, usernameLabel);

        myLayout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 50, SpringLayout.NORTH, usernameLabel);
        myLayout.putConstraint(SpringLayout.WEST, passwordTextField, 50, SpringLayout.EAST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, passwordTextField, 0, SpringLayout.NORTH, passwordLabel);

        myLayout.putConstraint(SpringLayout.EAST, confirmPasswordLabel, 0, SpringLayout.EAST, passwordLabel);
        myLayout.putConstraint(SpringLayout.NORTH, confirmPasswordLabel, 50, SpringLayout.NORTH, passwordLabel);
        myLayout.putConstraint(SpringLayout.WEST, confirmPasswordTextfield, 50, SpringLayout.EAST, confirmPasswordLabel);
        myLayout.putConstraint(SpringLayout.NORTH, confirmPasswordTextfield, 0, SpringLayout.NORTH, confirmPasswordLabel);

        myLayout.putConstraint(SpringLayout.WEST, errorLabel, 0, SpringLayout.WEST, confirmPasswordTextfield);
        myLayout.putConstraint(SpringLayout.NORTH, errorLabel, 30, SpringLayout.NORTH, confirmPasswordTextfield);

        myLayout.putConstraint(SpringLayout.WEST, createAccountButton, 0, SpringLayout.WEST, confirmPasswordTextfield);
        myLayout.putConstraint(SpringLayout.NORTH, createAccountButton, 50, SpringLayout.NORTH, confirmPasswordTextfield);
    }

    CreateAccountFrame () {

        myCreateAccountFrame = this;

        this.setSize(800, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set location of frame in the center of the window
        this.setLocationRelativeTo(null);

        SpringLayout myLayout = new SpringLayout();
        this.setLayout(myLayout);

        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(15);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField(15);

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordTextField = new JTextField(15);

        createAccountButton = new JButton("Create Account");

        errorLabel = new JLabel("TEST ERROR LABEL");
        errorLabel.setForeground(Color.RED);

        setElements(myLayout, usernameLabel, passwordLabel, confirmPasswordLabel,
            usernameTextField, passwordTextField, confirmPasswordTextField, 
            createAccountButton, errorLabel);

        addListeners();
        revalidate();
    }

    public void addListeners() {
        createAccountButton.addActionListener(new ActionListener() { // start recording on click
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Account Button Pressed!");

                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String confirmPassword = confirmPasswordTextField.getText();

                System.out.println("Username input: " + username);
                System.out.println("Password input " + password);
                System.out.println("Confirm password input " + confirmPassword);

                if (password.equals(confirmPassword)) {
                    Thread t = new Thread( // use another thread for answer computation to not lag UI
                    () -> {
                        CreateAccount.create(username, confirmPassword);
                        
                    });
                    t.start();
                    myCreateAccountFrame.dispose();
                    new AutoLoginFrame();
                } else if (!password.equals(confirmPassword)) {
                    errorLabel.setText("Your passwords do not match");
                } else {
                    errorLabel.setText("Your went wrong");
                }


                
            }
        });

    }
}

