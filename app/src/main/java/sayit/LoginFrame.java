
package sayit;

import java.awt.Color;
import java.awt.Dimension;
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

import java.awt.font.TextAttribute;

import javax.swing.WindowConstants;


public class LoginFrame extends JFrame {

    
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;


    private JButton logInButton;
    private JButton createAccountButton;

/*
     * Method for setting the location of the button in the footer area of the 
     * app
     */
    private void setElements(JLabel usernameLabel, JLabel passwordLabel, JTextField usernameTextField, JTextField passwordTextField) {
        this.add(usernameLabel);
        //this.add(usernameTextField);
        //this.add(passwordLabel);
        //this.add(passwordTextField);
    }


    LoginFrame () {
        this.setSize(800, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        SpringLayout myLayout = new SpringLayout();
        this.setLayout(myLayout);

        titleLabel = new JLabel("Say It Assitant");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(15);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField(15);

        logInButton = new JButton("Log In");
        createAccountButton = new JButton();
        createAccountButton.setBorderPainted(false);
        createAccountButton.setText("<HTML><U>Create Account</U></HTML>");
        createAccountButton.setContentAreaFilled(false);
        createAccountButton.setForeground(Color.BLUE);


        this.add(titleLabel);
        this.add(usernameLabel);
        this.add(usernameTextField);
        this.add(passwordLabel);
        this.add(passwordTextField);

        this.add(logInButton);
        this.add(createAccountButton);

        
        myLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 400, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, titleLabel, 80, SpringLayout.NORTH, this);

        myLayout.putConstraint(SpringLayout.WEST, usernameLabel, 250, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 150, SpringLayout.NORTH, this);
        myLayout.putConstraint(SpringLayout.WEST, usernameTextField, 50, SpringLayout.EAST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 0, SpringLayout.NORTH, usernameLabel);

        myLayout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 50, SpringLayout.NORTH, usernameLabel);
        myLayout.putConstraint(SpringLayout.WEST, passwordTextField, 50, SpringLayout.EAST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, passwordTextField, 0, SpringLayout.NORTH, passwordLabel);

        myLayout.putConstraint(SpringLayout.WEST, createAccountButton, 20, SpringLayout.WEST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, createAccountButton, 50, SpringLayout.NORTH, passwordLabel);
        myLayout.putConstraint(SpringLayout.WEST, logInButton, 30, SpringLayout.EAST, createAccountButton);
        myLayout.putConstraint(SpringLayout.NORTH, logInButton, 0, SpringLayout.NORTH, createAccountButton);

        //setElements(usernameLabel, passwordLabel, usernameTextField, passwordTextField);


        revalidate();
    }
}

