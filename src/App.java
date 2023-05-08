import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Footer extends JPanel {
    JButton askButton;
    JButton delButton;
    JButton clrButton;

    Color bgColor = new Color(50, 55, 65);
    
    Footer() {
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(bgColor);

        clrButton = new JButton("Clear All", null);
        this.add(clrButton);

        delButton = new JButton("Delete Question", null);
        this.add(delButton);

        askButton = new JButton("Ask Question", null);
        this.add(askButton);
    }

    public JButton getAskButton() {
        return askButton;
    }

    public JButton getClrButton() {
        return clrButton;
    }

    public JButton getDelButton() {
        return delButton;
    }
}

class AppFrame extends JFrame {
    //private SideBar sidebar;
    //private ChatShow chat;
    private Footer footer;

    private JButton askButton;
    private JButton delButton;
    private JButton clrButton;

    AppFrame() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //sidebar = new Sidebar();
        //chat = new Chat();
        footer = new Footer();

        //this.add(sidebar);
        //this.add(chat);
        this.add(footer);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        new AppFrame();
    }
}
