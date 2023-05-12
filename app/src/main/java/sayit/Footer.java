package sayit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/*
 * Footer subclass. Used to hold in askQuestion button.
 */
public class Footer extends JPanel{
    static Color RED = new Color(255, 0, 0);
    static Color BLUE = new Color(0,0,255);
    static Color GREEN = new Color(0, 255, 0);
    static Color YELLOW = new Color(255, 255, 0);
    static Color DARKGRAY = new Color(36,36,36);
    static Color MEDGRAY = new Color(65,65,65);
    static Color LIGHTGRAY = new Color(93,93,93);
    private Dimension FTSIZE = new Dimension(1000,100);

    Footer() {
        this.setBackground(MEDGRAY);
        this.setPreferredSize(FTSIZE);
        this.setMinimumSize(FTSIZE);
        this.setLayout(new BorderLayout());
    }
}