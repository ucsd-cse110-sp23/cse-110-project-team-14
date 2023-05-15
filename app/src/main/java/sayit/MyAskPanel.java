package sayit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
 * askPanel is one of the two panels that will be displayed on in the appframe. AskPanel will consists of three 
 * seperate panels, a footer, a query and response panel. The query and response panel will contain the query sent to
 * ChatGPT as well as its response. The footer panel currently contains the button to ask a question and receive an
 * answer. askPanel contains subclasses for all of these respective panels.
 */
public class MyAskPanel extends JPanel {
    /*Defining common variables */
    static Dimension PREFSIZE = new Dimension(1000,900);
    static Color RED = new Color(255, 0, 0);
    static Color BLUE = new Color(0,0,255);
    static Color GREEN = new Color(0, 255, 0);
    static Color YELLOW = new Color(255, 255, 0);
    static GridLayout HISTORYGRID = new GridLayout(0,1,5,5);
    static BorderLayout BORDERLAYOUT = new BorderLayout();
    static Color DARKGRAY = new Color(36,36,36);
    static Color MEDGRAY = new Color(65,65,65);
    static Color LIGHTGRAY = new Color(93,93,93);

    Question question = new Question();
    Answer answer = new Answer();

    /**
     * Getter for question field
     * @return question field
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Getter for answer field
     * @return answer field
     */
    public Answer getAnswer() {
        return answer;
    }
/*
* Constructor for askPanel. Creates a panel with BorderLayout that contains the footer, query and response panel.
*/
    MyAskPanel() {
        this.setBackground(DARKGRAY);
        this.setLayout(BORDERLAYOUT);
        this.setPreferredSize(PREFSIZE);
        this.add(question, BorderLayout.NORTH);
        this.add(answer, BorderLayout.CENTER);
        question.revalidate();
        this.revalidate();
    }

/*
* Question subclass. Used to hold response query. Contains a JTextArea that is populated by user query.
*/
    class Question extends JPanel{
        JLabel label = new JLabel("Question Asked");
        JTextArea text = new JTextArea(" ");
        private Dimension QSIZE = new Dimension (1000,300);

        public void updateText(String string){
            text.setText(string);
        }

        Question() {
            this.setBackground(MEDGRAY);
            this.setPreferredSize(QSIZE);
            this.setMinimumSize(QSIZE);
            this.setLayout(new BorderLayout());
            this.add(label, BorderLayout.PAGE_START);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBackground(DARKGRAY);
            label.setForeground(new Color(255,255,255));

            
            this.add(text, BorderLayout.CENTER);
            text.setAlignmentX(CENTER_ALIGNMENT);
            text.setAlignmentY(CENTER_ALIGNMENT);
        }
    }

/*
* Answer subclass. Used to hold response query. Contains a JTextArea that is populated by ChatGPT response.
*/
    class Answer extends JPanel{
        JLabel label = new JLabel("Response");
        JTextArea text = new JTextArea(" ");
        private Dimension ASIZE = new Dimension (1000,300);
        
        public void updateText(String string){
            text.setText(string);
        }

        Answer(){
            this.setBackground(LIGHTGRAY);
            this.setPreferredSize(ASIZE);
            this.setMinimumSize(ASIZE);
            this.setLayout(new BorderLayout());
            this.add(label, BorderLayout.PAGE_START);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setForeground(new Color(255,255,255));
            this.add(text, BorderLayout.CENTER);
            text.setAlignmentX(CENTER_ALIGNMENT);
            text.setAlignmentY(CENTER_ALIGNMENT);
        }
    }
}
