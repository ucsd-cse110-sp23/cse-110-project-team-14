package sayit;

import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.swing.JButton;


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
    EmailConnecter eConnecter;
    //private boolean askStop = false;
    final String URL = "http://localhost:8100/";

    public SendEmail(AudioRecorder r, JButton b, IAudioConverter con, IChatBot bot, MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co, AccountUIToServer connector,
        EmailConnecter eConnecter) {
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
        this.eConnecter = eConnecter;
    }

    public SendEmail() {

    }

    public void send(String toEmail) {
        /*String fromEmail, String toEmail,
        String smtpHost, String tlsPort, String password */
        eConnecter.getEmailInfo();
        String fromEmail = eConnecter.getEmail();
        String smtpHost = eConnecter.getSMTP();
        System.out.println("SMTP Host: " + smtpHost);
        String tlsPort = eConnecter.getTLS();
        System.out.println("TLS Port: " + tlsPort);
        String password = eConnecter.getPassword();
        String subject;
        String body;
        String prompt = buttonCoordinator.getCurButton().getText();
        String message = storage.getAnswer(prompt);
        String response;
        

        String command = "Send email to " + toEmail;
        frame.updateQuestionBox(command);
        
        //Seperate the subject and body in the message
        while (message.indexOf("\n") == 0) {
            message = message.substring(1);
        }

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
        String sentStatus = EmailUtil.sendEmail(session, toEmail, subject, body);
        if(sentStatus == "true") {
            response = "Email successfully sent.";
        } else {
            response =  sentStatus;
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
