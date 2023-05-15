package sayit;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;

public class ImportFiles {
    Storage storage;
    ButtonCoordinator co;
    Frame frame;
    File file;
    MySideBar sideBar;

    ImportFiles(Storage storage, ButtonCoordinator co,Frame frame, File file, MySideBar sideBar){
        this.storage = storage;
        this.co = co;
        this.frame = frame;
        this.file = file;
        this.sideBar = sideBar;
    }

    public void importFiles(){
        try{
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            while (buf.ready()) {
                String[] split = buf.readLine().split("[,]");
                storage.addQuestion(split[0], split[1]);
                JButton b = new JButton(split[0]);
                b.addActionListener(
                    (ActionEvent event) -> {
                        frame.updateQuestionBox(b.getText());
                        co.setCurButton(b);
                        frame.updateAnswerBox(storage.getAnswer(b.getText()));
                        System.out.println("BUTTON PRESSED");
                    }
                );
                sideBar.sideBarAddButton(b);
                co.setCurButton(b);
            }
            buf.close();
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("FILE NOT FOUND. ATTEMPTING TO MAKE FILE.");
            try{
                file.createNewFile();
                System.out.println("File made succesfully");
            }
            catch (Exception e){
                System.out.println("Error while trying to create new file");
            }
        }catch (Exception e){
            System.out.println("An exception occured while attempting to load files");
        }

    }
}
