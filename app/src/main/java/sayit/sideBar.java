package sayit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * The class sideBar is used to construct the sideBar in the JFrame for the SayItApp. The sideBar is a JScrollPane.
 * Within sideBar, there is a JPanel Header(header) and a JPanel(sideBarPanel) for aggregating content into the sideBar.
 * @author Apollo Larragoitia
 */
public class sideBar extends JScrollPane {
    private Dimension prefSize = new Dimension(900,900);
    private Dimension minSize = new Dimension(400,900);
    private Dimension minBoxSize = new Dimension(400, 19000);
    private GridLayout HISTORYGRID = new GridLayout(0,1,5,5);
    private BorderLayout BORDERLAYOUT = new BorderLayout();
    private Color DARKGRAY = new Color(36,36,36);
    private Color MEDGRAY = new Color(65,65,65);
    private Color LIGHTGRAY = new Color(93,93,93);
    public sideBarList sideBarList = new sideBarList();
    private sideBarPanel sideBarPanel;
    HashMap<String, JButton> buttonMap;

    /**
     * The following method creates and returns a JScrollPane. The JScrollPane will have its primary viewport set to
     * an instance of sideBarPanel.
     * @return JScrolLPane
     */
    public sideBar(){
        buttonMap = new HashMap<String,JButton>();
        sideBarPanel = new sideBarPanel();
        this.setViewportView(sideBarPanel);
        this.setVisible(true);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setMinimumSize(minSize);
        this.setMaximumSize(minSize);
    }

    public void sideBarAddButton(String string){
        JButton b = new JButton(string);
        sideBarList.add(b);
        buttonMap.put(string,b);
        
    }

    public void sideBarAddButton(JButton b){
        sideBarList.add(b);
        buttonMap.put(b.getText(),b);
    }

    public void deleteButton(JButton b){
        buttonMap.remove(b.getText());
        sideBarList.remove(b);
        sideBarList.revalidate();
        sideBarPanel.revalidate();
        this.revalidate();
    }

    public void deleteButton(String string){
        sideBarList.remove(buttonMap.get(string));
        buttonMap.remove(string);
        sideBarList.revalidate();
        sideBarList.repaint();
        sideBarPanel.revalidate();
        sideBarPanel.repaint();
        this.revalidate();
        this.repaint();
    }

    public void deleteAll(){
        buttonMap.clear();
        sideBarList.removeAll();
        sideBarList.revalidate();
    }

    public void revalidateComponents(){
        revalidate();
        sideBarPanel.revalidate();
        sideBarList.revalidate();
        revalidate();
    }

    /**
     * Imports from a txt file question and answer pairs into a HashMap for reference.
     * 
     * @param file
     * @return HashMap of question answer pairs
     */
    public HashMap<String,String> loadQuestions(File file){
        HashMap<String,String> questionAnswerMap = new HashMap<String,String>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            while(buf.ready()){
                String[] split = buf.readLine().split("[,]");
                questionAnswerMap.put(split[0], split[1]);
            }
            buf.close();
        } catch(Exception e) {

        }
        return questionAnswerMap;
    }

    /*
     * JPanel that the viewport of sideBar views. Contains a List Panel that dynamically updates with question history.
     */
    class sideBarPanel extends JPanel{
        JLabel sideBarHeader = new JLabel("Question History");
        
        sideBarPanel() {
            this.setLayout(BORDERLAYOUT);
            this.setMinimumSize(minSize);
            this.setPreferredSize(minSize);
            this.setBackground(LIGHTGRAY);
            this.setVisible(true);
            sideBarHeader.setHorizontalAlignment(JLabel.CENTER);
            sideBarHeader.setPreferredSize(new Dimension(200, 100));
            sideBarHeader.setBackground(DARKGRAY);
            sideBarHeader.setPreferredSize(new Dimension(300,200));
            sideBarHeader.setForeground(new Color(0,0,0));

            this.add(sideBarHeader, BorderLayout.PAGE_START);
            this.add(sideBarList);

            revalidate();
        }
    }

    class sideBarList extends JPanel{
        sideBarList(){
            this.setBackground(MEDGRAY);
            this.setLayout(HISTORYGRID);
        }

    }
}
