
package sayit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import javax.swing.WindowConstants;


public class LoginFrame extends JFrame {

    LoginFrame myLoginFrame;
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel errorLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JButton logInButton;
    private JButton createAccountButton;
    private ReadLoginInformation readLoginInformation;

    AccountUIToServer connecter;

    /*
     * Method for setting the location of the button in the footer area of the 
     * app
     */
    private void setElements(SpringLayout myLayout, JLabel titleLabel,
        JLabel usernameLabel, JLabel passwordLabel, 
        JTextField usernameTextField, JTextField passwordTextField,
        JButton logInButton, JButton createAccountButton, JLabel errorLabel) {
        
        this.add(titleLabel);
        this.add(usernameLabel);
        this.add(usernameTextField);
        this.add(passwordLabel);
        this.add(passwordTextField);
        this.add(logInButton);
        this.add(createAccountButton);
        this.add(errorLabel);

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

        myLayout.putConstraint(SpringLayout.WEST, errorLabel, 0, SpringLayout.WEST, passwordTextField);
        myLayout.putConstraint(SpringLayout.NORTH, errorLabel, 30, SpringLayout.NORTH, passwordTextField);

        myLayout.putConstraint(SpringLayout.WEST, createAccountButton, 20, SpringLayout.WEST, usernameLabel);
        myLayout.putConstraint(SpringLayout.NORTH, createAccountButton, 50, SpringLayout.NORTH, passwordLabel);
        myLayout.putConstraint(SpringLayout.WEST, logInButton, 30, SpringLayout.EAST, createAccountButton);
        myLayout.putConstraint(SpringLayout.NORTH, logInButton, 0, SpringLayout.NORTH, createAccountButton);
    }

    LoginFrame (AccountUIToServer connecter) {
        this.connecter = connecter;
        myLoginFrame = this;
        readLoginInformation = new ReadLoginInformation();

        

        this.setSize(800, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set location of frame in the center of the window
        this.setLocationRelativeTo(null);

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

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        setElements(myLayout, titleLabel, usernameLabel, passwordLabel, 
            usernameTextField, passwordTextField, logInButton,createAccountButton,
            errorLabel);

        addListeners();
        revalidate();
        // This code block is checking if the auto-login feature is enabled. If it is
        // enabled, it retrieves the saved username and password using the `readUsername()`
        // and `readPassword()` methods from the `readLoginInformation` object. It then
        // logs in the user using the `connecter.login()` method and creates a new `Frame`
        // object with `Whisper` and `ChatGPT` objects as parameters, along with the
        // `connecter` object. Finally, it disposes of the current `myLoginFrame` object.
        // Auto generated by Mintify
        if(readLoginInformation.autoLoginEnabled()){
            connecter.login(readLoginInformation.readUsername(),
            readLoginInformation.readPassword());
            new Frame(new Whisper(), new ChatGPT(), connecter);
            myLoginFrame.dispose();
        }


    }

    public void addListeners() {

        createAccountButton.addActionListener(new ActionListener() { // start recording on click
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Account Button Pressed!");
                
                new CreateAccountFrame(connecter);
                myLoginFrame.dispose();
            }
        });


        // This code is adding an action listener to the `delButton` JButton. When
        // the button is clicked, it creates a new thread that deletes the
        // currently selected question from the `storage` object and removes the
        // corresponding button from the `questionHistory` in sideBar. It also sets
        // the `currButton` variable to null, updates the answer
        // and question boxes to display empty strings, and revalidates the GUI
        // components. - Autogenerated by MINTLIFY
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();

                //System.out.println("Username input: " + username);
                //System.out.println("Password input " + password);
                //System.out.println("Log In Button Pressed!");

                Thread t = new Thread( // use another thread for answer computation to not lag UI
                    () -> {
                        String response = connecter.login(username, password);
                        if (response.equals("true")) {
                            new AutoLoginFrame(connecter,username,password);
                            myLoginFrame.dispose();
                        } else if (response.equals("Incorrect password")) {
                            errorLabel.setText(response);
                        } else if (response.equals("No account with that username")) {
                            errorLabel.setText(response);
                        } else {
                            errorLabel.setText("Something went wrong");
                        }
                });
                t.start();

            }
        });
    }
}

