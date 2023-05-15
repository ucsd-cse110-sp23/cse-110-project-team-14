package sayit;

public class ClearQuestions {
    
    
    Frame frame;
    Storage storage;
    MySideBar sideBar;
    ButtonCoordinator buttonCoordinator;


    public ClearQuestions(Frame f, Storage s, MySideBar bar, ButtonCoordinator c) {
        frame = f;
        storage = s;
        sideBar = bar;
        buttonCoordinator = c;
    }

    public void clearAll() {
        Thread t = new Thread(
            () -> {
                if (buttonCoordinator.getCurQ()) {
                    System.out.println("Unable to clear all. Currently asking question.");
                } else {
                    System.out.println("You clicked the clear all button!");
                    sideBar.deleteAll();
                    storage.clearAll();
                    buttonCoordinator.setCurButton(null);;
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
