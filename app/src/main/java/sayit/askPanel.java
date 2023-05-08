package sayit;

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.management.Query;
import javax.swing.*;
import java.util.*;
import java.util.Objects;

public class askPanel extends JPanel{
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
    Footer footer = new Footer();
    Answer answer = new Answer();

    public void updateQuestionText(String string){
        this.question.updateText(string);
    }

    public void updateAnswerText(String string){
        this.answer.updateText(string);
    }

    public Footer getFooter() {
        return footer;
    }


    askPanel(){
        this.setBackground(BLUE);
        this.setLayout(BORDERLAYOUT);
        this.setPreferredSize(PREFSIZE);
        this.add(footer, BorderLayout.SOUTH);
        this.add(question, BorderLayout.NORTH);
        this.add(answer, BorderLayout.CENTER);
        question.updateText("Test 4");
        question.revalidate();
        this.revalidate();
    }

    class Footer extends JPanel{
        static Dimension PREFSIZE = new Dimension(1000,100);
        Footer(){
            this.setBackground(RED);
            this.setPreferredSize(PREFSIZE);
            this.setMinimumSize(PREFSIZE);
        }
    }

    class Question extends JPanel{
        

        JLabel label = new JLabel("Question Asked");
        JTextArea text = new JTextArea("TEST3");
        static Dimension PREFSIZE = new Dimension (1000,300);

        public void updateText(String string){
            text.setText(string);
        }

        Question(){
            this.setBackground(GREEN);
            this.setPreferredSize(PREFSIZE);
            this.setMinimumSize(PREFSIZE);
            this.setLayout(new BorderLayout());
            this.add(label, BorderLayout.PAGE_START);
            label.setHorizontalAlignment(JLabel.CENTER);
            this.add(text, BorderLayout.CENTER);
            text.setAlignmentX(CENTER_ALIGNMENT);
            text.setAlignmentY(CENTER_ALIGNMENT);
        }
    }

    class Answer extends JPanel{
        JLabel label = new JLabel("Response");
        JTextArea text = new JTextArea("TEST 5");
        static Dimension PREFSIZE = new Dimension (1000,300);
        
        public void updateText(String string){
            text.setText(string);
        }

        Answer(){
            this.setBackground(BLUE);
            this.setPreferredSize(PREFSIZE);
            this.setMinimumSize(PREFSIZE);
            this.setLayout(new BorderLayout());
            this.add(label, BorderLayout.PAGE_START);
            label.setHorizontalAlignment(JLabel.CENTER);
            this.add(text, BorderLayout.CENTER);
            text.setAlignmentX(CENTER_ALIGNMENT);
            text.setAlignmentY(CENTER_ALIGNMENT);
        }
    }

}
    
