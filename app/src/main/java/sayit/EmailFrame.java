package sayit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;


public class EmailFrame extends JFrame {
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel displayLabel;
    private JLabel eAddressLabel;
    private JLabel SMTPHostLabel;
    private JLabel TLSPortLabel;
    private JLabel emailPassLabel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField displayField;
    private JTextField eAddressField;
    private JTextField SMTPHostField;
    private JTextField TLSPortField;
    private JTextField emailPassField;

    private JButton saveButton;
    private JButton cancelButton;

    /*
     * Method for setting the location of the button in the footer area of the 
     * app
     */
    private void setElements(GridBagLayout myLayout, GridBagConstraints confine, JLabel firstNameLabel, JLabel lastNameLabel, JLabel displayLabel, JLabel eAddressLabel, 
    JLabel SMTPHostLabel, JLabel TLSPortLabel, JLabel emailPassLabel, JTextField firstNameField, JTextField lastNameField, JTextField displayField, JTextField eAddressField, 
    JTextField SMTPHostField, JTextField TLSPortField, JTextField emailPassField, JButton cancelButton, JButton saveButton) {
        confine.gridx = 0;
        confine.gridy = 0;
        this.add(firstNameLabel, confine);
        confine.gridx = 1;
        this.add(firstNameField, confine);
        
        confine.gridx = 2;
        this.add(lastNameLabel, confine);
        confine.gridx = 3;
        this.add(lastNameField, confine);

        confine.gridx = 0;
        confine.gridy = 1;
        this.add(displayLabel, confine);
        confine.gridx = 1;
        this.add(displayField, confine);

        confine.gridx = 2;
        this.add(eAddressLabel, confine);
        confine.gridx = 3;
        this.add(eAddressField, confine);

        confine.gridx = 0;
        confine.gridy = 2;
        this.add(SMTPHostLabel, confine);
        confine.gridx = 1;
        this.add(SMTPHostField, confine);

        confine.gridx = 2;
        this.add(TLSPortLabel, confine);
        confine.gridx = 3;
        this.add(TLSPortField, confine);

        confine.gridx = 0;
        confine.gridy = 3;
        this.add(emailPassLabel, confine);
        confine.gridx = 1;
        this.add(emailPassField, confine);

        confine.gridx = 0;
        confine.gridy = 4;
        confine.gridwidth = 2;
        confine.fill = GridBagConstraints.HORIZONTAL;
        this.add(cancelButton, confine);
        confine.gridx = 2;
        this.add(saveButton, confine);
    }

    EmailFrame() {
        this.setSize(700, 350);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set location of frame in the center of the window
        this.setLocationRelativeTo(null);

        GridBagLayout myLayout = new GridBagLayout();
        this.setLayout(myLayout);

        GridBagConstraints confine = new GridBagConstraints();
        confine.insets = new Insets(15, 15, 15, 15);

        firstNameLabel = new JLabel("First Name");
        firstNameField = new JTextField(15);

        lastNameLabel = new JLabel("Last Name");
        lastNameField = new JTextField(15);

        displayLabel = new JLabel("Display Name");
        displayField = new JTextField(15);

        eAddressLabel = new JLabel("Email Address");
        eAddressField = new JTextField(15);

        SMTPHostLabel = new JLabel("SMTP Host");
        SMTPHostField = new JTextField(15);

        TLSPortLabel = new JLabel("TLS Port");
        TLSPortField = new JTextField(15);

        emailPassLabel = new JLabel("Email Password");
        emailPassField = new JTextField(15);

        cancelButton = new JButton("Cancel");
        saveButton = new JButton("Save");

        setElements(myLayout, confine, firstNameLabel, lastNameLabel, displayLabel, eAddressLabel, SMTPHostLabel, TLSPortLabel, emailPassLabel, 
        firstNameField, lastNameField, displayField, eAddressField, SMTPHostField, TLSPortField, emailPassField, cancelButton, saveButton);

        addListeners();
        revalidate();
    }

    public void addListeners() {

        cancelButton.addActionListener(new ActionListener() { // start recording on click
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel Button clicked!");
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Button clicked!");
            }
        });
    }
}