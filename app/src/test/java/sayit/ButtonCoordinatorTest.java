package sayit;

import javax.swing.JButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ButtonCoordinatorTest {
    private ButtonCoordinator myButtonCoordinator;

    @BeforeEach
    void setup() {
        myButtonCoordinator = new ButtonCoordinator();
    }
    
    
    @Test
    void testgetCurButton() {
        JButton wantedQuestion = new JButton();
        myButtonCoordinator.curButton = wantedQuestion;
        assertEquals(wantedQuestion,myButtonCoordinator.getCurButton());
    }

    @Test
    void testgetCurQ() {
        myButtonCoordinator.curQ = false;    
        assertEquals(false, myButtonCoordinator.getCurQ());

        myButtonCoordinator.curQ = true;    
        assertEquals(true, myButtonCoordinator.getCurQ());
    }

    @Test
    void testsetCurButton() {
        JButton actualButton = new JButton();
        //myButtonCoordinator.currButton = actualButton;
        myButtonCoordinator.setCurButton(actualButton);
        assertEquals(actualButton, myButtonCoordinator.curButton);
    }

   
    @Test
    void testsetCurQ() {
        myButtonCoordinator.setCurQ(false);
        assertEquals(false, myButtonCoordinator.curQ);

        myButtonCoordinator.setCurQ(true);
        assertEquals(true, myButtonCoordinator.curQ);
    }

}
