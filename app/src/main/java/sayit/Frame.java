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
    private JButton askButton;
    private JButton clrButton;
    private JButton delButton;
    private JButton voiceButton;
    private JButton currButton = null;
    private ButtonCoordinator buttonCoordinator;

    AskQuestion askQuestion;
    DeleteQuestion deleteQuestion;
    ClearQuestions clearQuestions;
    VoiceInterface voiceInterface;

    private boolean askStop = false;
    private boolean curQ = false;
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
    private void setButtons(Footer footer, JButton askButton, JButton delButton, JButton clrButton) {
        // Create 10 pixels of border around the buttons
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add 150 pixels of space from the left of the panel
        footer.add(Box.createRigidArea(new Dimension(150, 0)));
        footer.add(clrButton);

        // Make the extra space go between the two buttons to space them apart
        footer.add(Box.createHorizontalGlue());
        footer.add(delButton);

        footer.add(Box.createHorizontalGlue());
        footer.add(askButton);
        footer.add(Box.createRigidArea(new Dimension(150, 0)));
    }

    /*
     * Primary container for all panels. Frame is a JSplitPane, containing a fixed
     * set sideBar and a dynamic query and
     * response area for ChatGPT.
     */
    Frame(IAudioConverter converter, IChatBot chat) {
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
        askButton = new JButton("Ask Question");
        clrButton = new JButton("Clear All");
        delButton = new JButton("Delete Question");
        voiceButton = new JButton("Start");
        buttonCoordinator = new ButtonCoordinator();

        askQuestion = new AskQuestion(recorder, askButton, converter, chat, askPanel, this, storage, sideBar, buttonCoordinator);
        deleteQuestion = new DeleteQuestion(storage, currButton, sideBar, this, buttonCoordinator);
        clearQuestions = new ClearQuestions(this, storage, sideBar, buttonCoordinator);
        voiceInterface = new VoiceInterface(recorder, voiceButton, converter, chat, askPanel, this, storage, sideBar, buttonCoordinator,askQuestion, clearQuestions,deleteQuestion);
        
        
        this.add(splitPane, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        setButtons(footer, voiceButton, delButton, clrButton);
        importFiles = new ImportFiles(storage, buttonCoordinator, this, fileToLoad, sideBar);
        importFiles.importFiles();
        exportFiles = new ExportFiles(storage, fileToLoad);


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
                askQuestion.ask();
            }
        });

        voiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                voiceInterface.takeVoice();
            }
        });

        // This code is adding an action listener to the `delButton` JButton. When
        // the button is clicked, it creates a new thread that deletes the
        // currently selected question from the `storage` object and removes the
        // corresponding button from the `questionHistory` in sideBar. It also sets
        // the `currButton` variable to null, updates the answer
        // and question boxes to display empty strings, and revalidates the GUI
        // components. - Autogenerated by MINTLIFY
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteQuestion.delete();
            }
        });

        // This code is adding an action listener to the `clrButton` JButton. When
        // the button is clicked, it creates a new thread that checks if the user
        // is currently asking a question. If they are, it prints a message saying
        // that it is unable to clear all. If they are not, it deletes all the
        // buttons from the `questionHistory` in sideBar, clears all the questions
        // and answers from the `storage` object, sets the `currButton` 
        //  variable to null, updates the answer and question boxes
        // to display empty strings, and revalidates the GUI components.
        // Autogenerated by Mintlify
        clrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearQuestions.clearAll();
            }
        });

        // This code is adding a window listener to the `Frame` object. When the
        // user clicks the close button on the window, the `windowClosing` method
        // is called. This method exports the current state of the `storage` object
        // to a file and then exits the application by calling `System.exit(0)`.
        // This ensures that the data is saved before the application is closed.
        // Autogenerated by Mintlify 
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                exportFiles.exportFile();
                System.exit(0);
            }
        });
    }
}
