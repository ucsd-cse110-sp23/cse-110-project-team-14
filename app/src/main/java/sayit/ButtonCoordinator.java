package sayit;

import javax.swing.JButton;

public class ButtonCoordinator {
    JButton curButton = null;
    boolean curQ = false;
    ButtonCoordinator buttonCoordinator;

    public JButton getCurButton() {
        return curButton;
    }

    /**
     * true if question is currently being asked
     */
    public boolean getCurQ() {
        return curQ;
    }

    public void setCurButton(JButton b) {
        curButton = b;
    }

    /**
     * 
     * @param state true if you are waiting for response false otherwise
     */
    public void setCurQ(boolean state) {
        curQ = state;
    }
}
