package sayit;

import javax.swing.JButton;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

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
    private boolean askStop = false;
    final String URL = "http://localhost:8100/";

    public SendEmail(AudioRecorder r, JButton b, IAudioConverter con, IChatBot bot, MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co) {
        recorder = r;
        askButton = b;
        converter = con;
        chat = bot;
        askPanel = pan;
        frame = f;
        storage = s;
        sideBar = bar;
        buttonCoordinator = co;
    }

    public SendEmail() {

    }

    public void send(String fromEmail, String toEmail,
        String smtpHost, String tlsPort, String password) {
        String message = storage.getAnswer(buttonCoordinator.getCurButton().getText());
        
        System.out.println(message); 
        
        //Seperate the subject from the message
        String subject = message.substring(message.indexOf("Subject:") + 9, message.indexOf("\n"));
        System.out.println("Subject: " + subject);

        //Seperate the body from the message
        String body = message.substring(message.indexOf("\n") + 2);
        System.out.println("body: " + body);

		System.out.println("TLS Email Start");
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
		
		EmailUtil.sendEmail(session, toEmail, subject, body);
        
    }

    public static void main(String[] args) {
        
        SendEmail sendEmail = new SendEmail();
        sendEmail.send("dennisliang01@gmail.com",
            "dbliang@ucsd.edu", "", "", "");
        
    }
}
