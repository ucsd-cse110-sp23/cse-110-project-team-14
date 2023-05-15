package sayit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Story3Test3 {
    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage();
    }

    @Test
    public void testDeleteQuestionWhileAsk() {
        // Add some questions and answers to the storage
        Storage storage = new Storage();
        JButton currButton = new JButton();
        MySideBar sideBar = new MySideBar();

        deleteq = new DeleteQuestion(Storage storage, JButton currButton, MySideBar sideBar, Frame frame, ButtonCoordinator buttonCoordinator);
    }
}
