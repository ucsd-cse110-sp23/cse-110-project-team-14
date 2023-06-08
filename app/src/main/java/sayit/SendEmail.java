package sayit;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class SendEmail {
    AudioRecorder recorder;
    JButton askButton;
    IAudioConverter converter;
    IChatBot chat;
    MyAskPanel askPanel;
    Frame frame;
    Storage storage;
    MySideBar sideBar;
    ButtonCoordinator buttonCoordinator;
    AccountUIToServer connector;
    private boolean askStop = false;
    final String URL = "http://localhost:8100/";

    public SendEmail(AudioRecorder r, JButton b, IAudioConverter con, IChatBot bot, MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co, AccountUIToServer connector) {
        recorder = r;
        askButton = b;
        converter = con;
        chat = bot;
        askPanel = pan;
        frame = f;
        storage = s;
        sideBar = bar;
        buttonCoordinator = co;
        this.connector = connector;
    }

    public SendEmail() {

    }

    public void send(String fromEmail, String toEmail,
        String smtpHost, String tlsPort, String password) {
        String subject;
        String body;
        String prompt = buttonCoordinator.getCurButton().getText();
        String message = storage.getAnswer(prompt);
        //System.out.println(message);
        String response;

        String command = "Send email to " + toEmail;
        frame.updateQuestionBox(command);
        
        //Seperate the subject and body in the message
        if (message.indexOf("Subject:") == 0) {
            subject = message.substring(message.indexOf("Subject:") + 9, message.indexOf("\n"));
            body = message.substring(message.indexOf("\n") + 2);
            
        } else {
            subject = prompt;
            body = message;
        }
        
        System.out.println("Subject: " + subject);
        System.out.println("body: " + body);
        
        
        //System.out.println("TLS Email Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost); //SMTP Host
        props.put("mail.smtp.port", tlsPort); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        
        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        
        if(EmailUtil.sendEmail(session, toEmail, subject, body)) {
            response = "Email successfully sent.";
        } else {
            response =  "Something went wrong";
        }
        frame.updateAnswerBox(response);


        storage.addQuestion(command, response);
        askPanel.revalidate();
        JButton b = new JButton(command);
        b.addActionListener(
                (ActionEvent event) -> {
                    frame.updateQuestionBox(b.getText());
                    buttonCoordinator.setCurButton(b);
                    frame.updateAnswerBox(storage.getAnswer(b.getText()));
                });
        sideBar.sideBarAddButton(b);
        buttonCoordinator.setCurButton(b);
        buttonCoordinator.setCurQ(false);
        System.out.println(connector.getUsername());
        DBAddCommand.addCommand(command, response, connector.getUsername());
        
    }
}
