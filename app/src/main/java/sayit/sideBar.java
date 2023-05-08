package sayit;

import java.awt.event.*;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.awt.*;
import javax.management.Query;
import javax.swing.*;
import java.util.*;



/**
 * The class sideBar is used to construct the sideBar in the JFrame for the SayItApp. The sideBar is a JScrollPane.
 * Within sideBar, there is a JPanel Header(header) and a JPanel(sideBarPanel) for aggregating content into the sideBar.
 * @author Apollo Larragoitia
 */
public class sideBar extends JScrollPane {
    static Dimension prefSize = new Dimension(900,900);
    static Dimension minSize = new Dimension(400,900);
    static Dimension minBoxSize = new Dimension(400, 19000);
    static GridLayout HISTORYGRID = new GridLayout(0,1,5,5);
    static BorderLayout BORDERLAYOUT = new BorderLayout();
    static Color BLUE = new Color(0,0,255);
    static Color RED = new Color(255,0,0);
    static Color GREEN = new Color(0,0,255);
    static Color DARKGRAY = new Color(36,36,36);
    static Color MEDGRAY = new Color(65,65,65);
    static Color LIGHTGRAY = new Color(93,93,93);
    static sideBarPanel sideBarPanel;
    static JPanel sideBarHeader;
    ArrayList<JButton> buttonArray;

    /**
     * The following method creates and returns a JScrollPane. The JScrollPane will have its primary viewport set to
     * an instance of sideBarPanel.
     * @return JScrolLPane
     */
    public sideBar(){
        sideBarPanel = new sideBarPanel();
        this.setViewportView(sideBarPanel);
        this.setVisible(true);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setMinimumSize(minSize);
        this.setMaximumSize(minSize);
    }

    public void sideBarAddButton(String string){
        JButton b = new JButton(string);
        sideBarPanel.sideBarList.add(b);
    }

    public void sideBarAddButton(JButton b){
        sideBarPanel.sideBarList.add(b);
    }

    /**
     * Imports from a txt file question and answer pairs into a HashMap for reference.
     * 
     * @param file
     * @return HashMap of question answer pairs
     */
    public HashMap<String,String> loadQuestions(File file){
        HashMap<String,String> questionAnswerMap = new HashMap<String,String>();
        try{
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            while(buf.ready()){
                String[] split = buf.readLine().split("[,]");
                questionAnswerMap.put(split[0], split[1]);
            }
        }
        catch(Exception e){}
        return questionAnswerMap;
    }

    /*
     * JPanel that the viewport of sideBar views. Contains a List Panel that dynamically updates with question history.
     */
    class sideBarPanel extends JPanel{
        JLabel sideBarHeader = new JLabel("Question History");
        JPanel sideBarList = new sideBarList();
        
        sideBarPanel(){
            this.setLayout(BORDERLAYOUT);
            this.setMinimumSize(minSize);
            this.setPreferredSize(minSize);
            sideBarHeader.setHorizontalAlignment(JLabel.CENTER);
            sideBarHeader.setPreferredSize(new Dimension(200, 100));
            sideBarHeader.setBackground(DARKGRAY);
            sideBarHeader.setPreferredSize(new Dimension(300,200));
            sideBarHeader.setForeground(new Color(0,0,0));
            this.add(sideBarHeader,BORDERLAYOUT.PAGE_START);
            this.add(sideBarList);
            this.setBackground(LIGHTGRAY);
            this.setVisible(true);
            this.revalidate();
        }
    }

    class sideBarList extends JPanel{
        sideBarList(){
            this.setBackground(MEDGRAY);
            this.setLayout(HISTORYGRID);
        }
    }

}
