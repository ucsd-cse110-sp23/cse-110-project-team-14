package sayit;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class AskQuestion {
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

    public AskQuestion(AudioRecorder r, JButton b, IAudioConverter con, IChatBot bot, MyAskPanel pan, Frame f, Storage s, MySideBar bar, ButtonCoordinator co) {
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

    public void ask() {
        if (askStop) {
            recorder.stopRecording();
            askButton.setText("Ask Question"); // change button text
            askStop = false; // change toggle
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
                                String questionTime = question +" "+ dtf.format(currTime);
                                frame.updateQuestionBox(question);
                                String answer = chat.askQuestion(question);
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
            t.start(); // start the new thread
        } else {
            recorder.startRecording();
            askButton.setText("Stop Question"); // change text back
            System.out.println("CurQ = true");
            buttonCoordinator.setCurQ(true);
            askStop = true; // change toggle back
        }
    }
}
