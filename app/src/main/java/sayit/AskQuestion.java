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
import java.util.concurrent.TimeUnit;

public class AskQuestion {
    AudioRecorder recorder;
    IAudioConverter converter;
    IChatBot chat;
    MyAskPanel askPanel;
    Frame frame;
    Storage storage;
    MySideBar sideBar;
    ButtonCoordinator buttonCoordinator;
    private boolean askStop = false;
    final String URL = "http://localhost:8100/";

    public AskQuestion(AudioRecorder r, IAudioConverter con, IChatBot bot, MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co) {
        recorder = r;
        converter = con;
        chat = bot;
        askPanel = pan;
        frame = f;
        storage = s;
        sideBar = bar;
        buttonCoordinator = co;
    }

    public String askHTTPRequest(String query){
        String response = "HTTP REQUEST SENT";

        try{
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(query);
            out.flush();
            out.close();


            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            //Debugging MUST KEEP OR CODE BREAKS FOR SOME REASON
            //TODO possible error here? sometimes when asking who is louis braille it reads null
            //code sometimes doesn't even get here.
            System.out.println("\nREADING LINES FROM BUFFER\n");            
            String temp = "";
            response = in.readLine();
            System.out.println(response);
            while(temp != null) {
                response += temp + "\n";
                System.out.println(temp);
                temp = in.readLine();
            }
            in.close();
            
            return response;

        }catch (MalformedURLException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }catch (IOException exception){
            exception.printStackTrace();
            
            System.out.println(exception.getMessage());
        }
        return response;
    }


    public void ask(String question){
        Thread t = new Thread(
            () -> {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/uuuu");
                LocalDateTime currTime = LocalDateTime.now();
                try {
                    /*
                     * Takes a recording and transcribes it into text using Whisper. After
                     * transcription, the
                     * string is saved as a question. Then, that string is asked to ChatGPT. The
                     * question/answer
                     * pairs are then stored and displayed in the GUI.
                     */
                    if (question.equals("")) {
                        frame.updateQuestionBox("Microphone didn't pickup any sound");
                        frame.updateAnswerBox("");
                        askPanel.revalidate();
                        buttonCoordinator.setCurQ(false);
                        buttonCoordinator.setCurButton(null);
                    } else {
                        String questionTime = "Question: " + question +"\t \n"+  "Time: " + dtf.format(currTime);
                        frame.updateQuestionBox(questionTime);
                        String answer = askHTTPRequest(questionTime);
                        System.out.println(answer);
                        frame.updateAnswerBox(answer);
                        storage.addQuestion(questionTime, answer);
                        askPanel.revalidate();
                        JButton b = new JButton(questionTime);
                        b.addActionListener(
                                (ActionEvent event) -> {
                                    frame.updateQuestionBox(b.getText());
                                    buttonCoordinator.setCurButton(b);
                                    frame.updateAnswerBox(storage.getAnswer(b.getText()));
                                    System.out.println("BUTTON PRESSED");
                                });
                        sideBar.sideBarAddButton(b);
                        buttonCoordinator.setCurButton(b);
                        buttonCoordinator.setCurQ(false);
                        System.out.println("CurQ = false");
                    }
                } catch (Exception ex) {
                    System.out.println("Error occured");
                }
            }); 
            t.start();
    }


    public void ask() {
        Thread t = new Thread( // use another thread for answer computation to not lag UI
            () -> {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/uuuu");
                LocalDateTime currTime = LocalDateTime.now();
                try {
                    /*
                     * Takes a recording and transcribes it into text using Whisper. After
                     * transcription, the
                     * string is saved as a question. Then, that string is asked to ChatGPT. The
                     * question/answer
                     * pairs are then stored and displayed in the GUI.
                     */
                    String question = converter.audioToString();
                    if (question.equals("")) {
                        frame.updateQuestionBox("Microphone didn't pickup any sound");
                        frame.updateAnswerBox("");
                        askPanel.revalidate();
                        buttonCoordinator.setCurQ(false);
                        buttonCoordinator.setCurButton(null);
                    } else {
                        String questionTime = question +"\t"+ dtf.format(currTime);
                        frame.updateQuestionBox(questionTime);
                        String answer = askHTTPRequest(questionTime);
                        System.out.println("Answer:" + answer);
                        frame.updateAnswerBox(answer);
                        storage.addQuestion(questionTime, answer);
                        askPanel.revalidate();
                        JButton b = new JButton(questionTime);
                        b.addActionListener(
                                (ActionEvent event) -> {
                                    frame.updateQuestionBox(b.getText());
                                    buttonCoordinator.setCurButton(b);
                                    frame.updateAnswerBox(storage.getAnswer(b.getText()));
                                    System.out.println("BUTTON PRESSED");
                                });
                        sideBar.sideBarAddButton(b);
                        buttonCoordinator.setCurButton(b);
                        buttonCoordinator.setCurQ(false);
                        System.out.println("CurQ = false");
                    }

                } catch (Exception ex) {
                    System.out.println("Error occured");
                }
            });
        t.start(); // start the new thread
    }
}
