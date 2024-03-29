package sayit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

// This code is defining a new class called `Frame` that extends the `JFrame` class
// in Java's Swing library. This class is used to create the main window of the
// application and contains various GUI components such as a sidebar, query and
// response area, and footer. It also contains methods for updating the text
// displayed in the query and response boxes, setting up buttons and their
// listeners, and initializing the audio recorder, audio converter, and chatbot
// objects used in the application. - Autogenerated by MINTLIFY
public class Frame extends JFrame {
    private MySideBar sideBar;
    private MyAskPanel askPanel;
    private JSplitPane splitPane;
    private Footer footer;

    private AudioRecorder recorder;
    private JButton voiceButton;
    private JButton currButton = null;
    private ButtonCoordinator buttonCoordinator;

    AskQuestion askQuestion;
    DeleteQuestion deleteQuestion;
    ClearQuestions clearQuestions;
    CreateEmail createEmail;
    SendEmail sendEmail;
    VoiceInterface voiceInterface;

    ImportFiles importFiles;
    ExportFiles exportFiles;

    static Storage storage = new Storage();

    IAudioConverter converter;
    IChatBot chat;
    File fileToLoad = new File("questions.txt");

    // These two methods are updating the text displayed in the GUI for the question
    // and answer boxes. They take in a string parameter and pass it to the
    // corresponding method in the `askPanel` object, which is responsible for
    // displaying the question and answer text. - Autogenerated by MINTLIFY
    public void updateQuestionBox(String string) {
        askPanel.getQuestion().updateText(string);
    }

    public void updateAnswerBox(String string) {
        askPanel.getAnswer().updateText(string);
    }

    /*
     * Method for setting the location of the clear all, delete and ask question
     * in the footer area of the app
     */
    private void setButtons(Footer footer, JButton voiceButton) {
        // Create 10 pixels of border around the buttons
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footer.add(Box.createHorizontalGlue());
        footer.add(voiceButton);
        footer.add(Box.createHorizontalGlue());
    }

    /*
     * Primary container for all panels. Frame is a JSplitPane, containing a fixed
     * set sideBar and a dynamic query and
     * response area for ChatGPT.
     */
    Frame(IAudioConverter converter, IChatBot chat, AccountUIToServer connecter, EmailConnecter eConnecter) {
        this.converter = converter;
        this.chat = chat;

        this.setSize(1600, 900);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sideBar = new MySideBar();
        askPanel = new MyAskPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideBar, askPanel);
        footer = new Footer();
        recorder = new AudioRecorder();
        voiceButton = new JButton("Start");
        buttonCoordinator = new ButtonCoordinator();

        askQuestion = new AskQuestion(recorder, converter, chat, askPanel, this, storage, sideBar, buttonCoordinator);
        deleteQuestion = new DeleteQuestion(storage, currButton, sideBar, this, buttonCoordinator);
        clearQuestions = new ClearQuestions(this, storage, sideBar, buttonCoordinator);
        createEmail = new CreateEmail(recorder, voiceButton, converter, chat, askPanel, this, storage, sideBar, buttonCoordinator, connecter, eConnecter);
        sendEmail = new SendEmail(recorder, voiceButton, converter, chat, askPanel, this, storage, sideBar, buttonCoordinator, connecter, eConnecter);

        voiceInterface = new VoiceInterface(recorder, voiceButton, converter, chat, 
            askPanel, this, storage, sideBar, buttonCoordinator, askQuestion, 
            clearQuestions, deleteQuestion, createEmail, sendEmail, connecter, eConnecter);
        
        
        this.add(splitPane, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        setButtons(footer, voiceButton);
        importFiles = new ImportFiles(storage, buttonCoordinator, this, fileToLoad, sideBar);
        importFiles.importFiles(connecter.getUsername());
        exportFiles = new ExportFiles(storage, fileToLoad);
        CheckServerStatus serverStatus = new CheckServerStatus(this);


        addListeners();
        serverStatus.updateServerStatus();
        revalidate();
    }

    /*
     * Adds a listener to the `Ask Question` Button
     */
    public void addListeners() {

        voiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Voice interface");
                voiceInterface.takeVoice();
            }
        });

    }
}
