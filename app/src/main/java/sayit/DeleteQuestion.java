package sayit;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteQuestion{
    Storage storage;
    JButton currButton;
    MySideBar sideBar;
    Frame frame;
    ButtonCoordinator buttonCoordinator;

    public DeleteQuestion(Storage storage, JButton currButton, MySideBar sideBar,Frame frame, ButtonCoordinator buttonCoordinator) {
        this.storage = storage;
        this.currButton = currButton;
        this.sideBar = sideBar;
        this.buttonCoordinator = buttonCoordinator;
        this.frame = frame;
    }

    public void delete() {
                Thread t = new Thread(
                        () -> {
                            if (buttonCoordinator.getCurQ() == true){
                                return;
                            }

                            else{     
                                System.out.println("Deleted Question");
                                storage.deleteQuestion(buttonCoordinator.getCurButton().getText());
                                sideBar.deleteButton(buttonCoordinator.getCurButton());
                                buttonCoordinator.setCurButton(null);
                                frame.updateAnswerBox(" ");
                                frame.updateQuestionBox(" ");
                                sideBar.revalidate();
                                sideBar.repaint();
                                frame.revalidate();
                            }

                        });
                t.start();
           }
}
