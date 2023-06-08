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

public class CreateEmail {
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

    public CreateEmail(AudioRecorder r, JButton b, IAudioConverter con, IChatBot bot, MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co) {
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

    public String askHTTPRequest(String query){
        String response = "";
        String inputLine = "";

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

            //Debugging
            System.out.println("READING LINES FROM BUFFER");
            in.readLine();
            in.readLine();

            
            inputLine = in.readLine();
            //response = inputLine;

            while(inputLine != null) {
                response = response + inputLine + "\n";
                inputLine = in.readLine();
            }

            System.out.println("Reponse: " + response);

            in.close();
            System.out.println("\n CLIENT: RETURNING RESPONSE FROM SERVER" + response);
            
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


    public void create(String message){
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
                    if (message.equals("")) {
                        frame.updateQuestionBox("Microphone didn't pickup any sound");
                        frame.updateAnswerBox("");
                        askPanel.revalidate();
                        buttonCoordinator.setCurQ(false);
                        buttonCoordinator.setCurButton(null);
                    } else {
                        String questionTime = message +"\t \n" +  "Time: " + dtf.format(currTime);
                        //String questionTime = message +"\t"+ dtf.format(currTime);
                        frame.updateQuestionBox(questionTime);
                        String answer = askHTTPRequest(questionTime);
                        System.out.println(answer);
                        
                        answer = addSignature(answer, "Dennis Liang");
                        frame.updateAnswerBox(answer);
                        storage.addQuestion(questionTime, answer);
                        askPanel.revalidate();
                        JButton b = new JButton(questionTime);
                        b.addActionListener(
                                (ActionEvent event) -> {
                                    frame.updateQuestionBox(b.getText());
                                    buttonCoordinator.setCurButton(b);
                                    frame.updateAnswerBox(storage.getAnswer(b.getText()));
                                    //System.out.println("BUTTON PRESSED");
                                });
                        sideBar.sideBarAddButton(b);
                        buttonCoordinator.setCurButton(b);
                        buttonCoordinator.setCurQ(false);
                        //System.out.println("CurQ = false");
                    }
                } catch (Exception ex) {
                    System.out.println("Error occured");
                }
            }); 
            t.start();
    }

    public String addSignature(String message, String displayName) {
        String modifiedMessage = message.replace("[Your Name]", displayName);
        //answer = answer.replace("[Name]", "Dennis Liang");
        //answer = answer.replace("[Name]", "Dennis Liang");
        return modifiedMessage;
    }
}
