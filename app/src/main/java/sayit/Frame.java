package sayit;

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.management.Query;
import javax.swing.*;
import java.util.*;
import java.util.Objects;

public class Frame extends JFrame {
    sideBar questionHistory;
    static askPanel chatGPT;
    JSplitPane splitPane;
    private AudioRecorder recorder;
    private JButton askButton;
    private boolean askStop = false;
    static Storage storage = new Storage();

    public void updateQuestionBox(String string){
        chatGPT.updateQuestionText(string);
    }

    public void updateAnswerBox(String string){
        chatGPT.updateAnswerText(string);
    }

    public JPanel getAnswerFooter(){
        return chatGPT.getFooter();
    }

    /*
     * Primary container for all panels. Frame is a JSplitPane, containing a fixed set sideBar and a dynamic query and
     * response area for ChatGPT.
     */
    Frame(){
        this.setSize(1600,900);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        questionHistory = new sideBar();
        chatGPT = new askPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,questionHistory,chatGPT);
        this.add(splitPane, BorderLayout.CENTER);

        recorder = new AudioRecorder();
        askButton = new JButton("Ask Question");
        this.getAnswerFooter().add(askButton);
        addListeners();

        this.revalidate();
    }
    public static void main(String[] args) {
        Frame frame = new Frame();
    }

    /*
     * Adds a listener to the `Ask Question` Button
     */
    public void addListeners() {
        askButton.addActionListener(new ActionListener() { // start recording on click
            @Override
            public void actionPerformed(ActionEvent e) {
                if (askStop) {
                    recorder.stopRecording();
                    askButton.setText("Ask Question"); //change button text
                    askStop = false; // change toggle
                    Thread t = new Thread( // use another thread for answer computation to not lag UI
                        () -> {
                        try {

                            /*
                             * Takes a recording and transcribes it into text using Whisper. After transcription, the 
                             * string is saved as a question. Then, that string is asked to ChatGPT. The question/answer
                             * pairs are then stored and displayed in the GUI.
                             */
                            String question = Whisper.audioToString();
                            chatGPT.updateQuestionText(question);
                            chatGPT.revalidate();
                            String answer = ChatGPT.askQuestion(question);
                            System.out.println(answer);
                            chatGPT.updateAnswerText(answer);
                            chatGPT.revalidate();
                            storage.addQuestion(question, answer);
                            JButton b = new JButton(question);
                            b.addActionListener(
                                (ActionEvent event) -> {
                                    updateQuestionBox(b.getText());
                                    updateAnswerBox(storage.getAnswer(b.getText()));
                                    System.out.println("BUTTON PRESSED");
                                }
                            );
                            questionHistory.sideBarAddButton(b);
                        } catch (Exception ex) {
                            System.out.println("Error occured");
                        }
                    }
                    );
                    t.start(); // start the new thread
                } else {
                    recorder.startRecording();
                    askButton.setText("Stop Question"); // change text back
                    askStop = true; // change toggle back
                }
            }
            }
        );
    }

}
