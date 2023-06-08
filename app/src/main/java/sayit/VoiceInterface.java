package sayit;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class VoiceInterface {
    AudioRecorder recorder;
    JButton startButton;
    IAudioConverter converter;
    IChatBot chat;
    MyAskPanel askPanel;
    Frame frame;
    Storage storage;
    MySideBar sideBar;
    ButtonCoordinator buttonCoordinator;
    private boolean askStop = false;
    final String URL = "http://localhost:8100/";
    ParseVoice parseVoice;
    AskQuestion askQuestion;
    ClearQuestions clearQuestions;
    DeleteQuestion deleteQuestion;
    CreateEmail createEmail;
    SendEmail sendEmail;
    String query;
    String intent;
    AccountUIToServer connector;

    public VoiceInterface(AudioRecorder r, JButton b, IAudioConverter con, IChatBot bot, 
        MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co, 
        AskQuestion ask, ClearQuestions clearQuestions, DeleteQuestion deleteQuestion,
        CreateEmail createEmail, SendEmail sendEmail, AccountUIToServer connecter) {

        recorder = r;
        startButton = b;
        converter = con;
        chat = bot;
        askPanel = pan;
        frame = f;
        storage = s;
        sideBar = bar;
        buttonCoordinator = co;
        parseVoice = new ParseVoice();
        askQuestion = ask;
        this.clearQuestions = clearQuestions;
        this.deleteQuestion = deleteQuestion;
        this.createEmail = createEmail;
        this.sendEmail = sendEmail;
        this.connector = connector;
    }
    
    public void takeVoice(){
        if (askStop){
            recorder.stopRecording();
            startButton.setText("Start");
            askStop = false;
            //System.out.println("CurQ = false");
            Thread t = new Thread(
                () -> {
                    try{
                        String input = converter.audioToString();
                        String intent = parseVoice.parseIntent(input);
                        String query = parseVoice.parseQuery(input);
                        System.out.println(input);
                        System.out.println("");
                        System.out.println("Intent is : " + intent);
                        System.out.println("Query is: " + query);
                        buttonCoordinator.setCurQ(false);
                        record(intent, query);
                    }
                    catch(IOException e){
                    }
                }
            );
            t.start();
        }
        else{
            recorder.startRecording();
            startButton.setText("Listening...");
            //System.out.println("CurQ = true");
            buttonCoordinator.setCurQ(true);
            askStop = true;
        }
        
    }

    public void record(String intent, String query){
        switch(intent){
            case "Question": 
                askQuestion.ask(query);
                break;
            case "Clear All": 
                System.out.println("Clear All Switch");
                clearQuestions.clearAll();
                break;
            case "Delete Prompt": 
                System.out.println("Delete Switch");
                deleteQuestion.delete();
                break;
            case "Create Email":
                System.out.println("Create Email Switch");
                createEmail.create(query);
                break;
            case "Send Email":
                System.out.println("Send Email Swtich");
                sendEmail.send("dennisliang01@gmail.com", query, 
                    "smtp.gmail.com", "586", "abrqfnuvepunucxt");
                break;

            default: System.out.println("ERROR!");
        }
        
    }



}
