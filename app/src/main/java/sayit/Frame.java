package sayit;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

public class Frame extends JFrame {
    sideBar questionHistory;
    askPanel askPanel;
    JSplitPane splitPane;
    Footer footer;
    private AudioRecorder recorder;
    private JButton askButton;
    private JButton clrButton;
    private JButton delButton;
    private boolean askStop = false;
    private String curQuestion = null;
    private JButton currButton = null;
    static Storage storage = new Storage();

    IAudioConverter converter;
    IChatBot chat;

    public void updateQuestionBox(String string){
        askPanel.updateQuestionText(string);
    }

    public void updateAnswerBox(String string){
        askPanel.updateAnswerText(string);
    }

    public void revalidateSideBar(){
        questionHistory.revalidateComponents();
    }
    
    private void setButtons(Footer footer, JButton askButton, JButton delButton, JButton clrButton) {
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add 150 pixels of space from the left of the panel
        footer.add(Box.createRigidArea(new Dimension(150, 0)));
        footer.add(clrButton);
        
        //Make the extra space go between the two buttons to space them apart
        footer.add(Box.createHorizontalGlue());
        footer.add(delButton);

        footer.add(Box.createHorizontalGlue());
        footer.add(askButton);
        footer.add(Box.createRigidArea(new Dimension(150, 0)));
    }

    /*
     * Primary container for all panels. Frame is a JSplitPane, containing a fixed set sideBar and a dynamic query and
     * response area for ChatGPT.
     */
    Frame(IAudioConverter converter, IChatBot chat) {
        this.converter = converter;
        this.chat = chat;

        this.setSize(1600,900);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        questionHistory = new sideBar();
        askPanel = new askPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, questionHistory, askPanel);
        footer = new Footer();
        recorder = new AudioRecorder();
        askButton = new JButton("Ask Question");
        clrButton = new JButton("Clear All");
        delButton = new JButton("Delete Question");
        this.add(splitPane, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        setButtons(footer, askButton, delButton, clrButton);

        addListeners();
        revalidate();
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
                            String question = converter.audioToString();
                            if(question.equals("")) {
                                askPanel.updateQuestionText("Microphone didn't pickup any sound");
                                curQuestion = null;
                                askPanel.revalidate();
                            } else {
                                askPanel.updateQuestionText(question);
                                String answer = chat.askQuestion(question);
                                System.out.println(answer);
                                askPanel.updateAnswerText(answer);
                                storage.addQuestion(question, answer);
                                askPanel.revalidate();
                                JButton b = new JButton(question);
                                b.addActionListener(
                                    (ActionEvent event) -> {
                                        updateQuestionBox(b.getText());
                                        curQuestion = b.getText();
                                        currButton = b;
                                        updateAnswerBox(storage.getAnswer(b.getText()));
                                        System.out.println("BUTTON PRESSED");
                                    }
                            );
                            questionHistory.sideBarAddButton(b);
                            currButton = b;
                            }
                            
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

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Deleted Question");
                storage.deleteQuestion(currButton.getText());
                questionHistory.deleteButton(currButton);
                currButton = null;
                curQuestion = null;
                updateAnswerBox(" ");
                updateQuestionBox(" ");
                revalidateSideBar();
                splitPane.revalidate();
                revalidate();
            }
        });

        clrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the clear all button!");
            }
        });

        
    }
}
