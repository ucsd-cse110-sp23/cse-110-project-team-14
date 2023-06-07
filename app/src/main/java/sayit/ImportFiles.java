package sayit;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


public class ImportFiles {
    Storage storage;
    ButtonCoordinator co;
    Frame frame;
    File file;
    MySideBar sideBar;

    ImportFiles(Storage storage, ButtonCoordinator co, Frame frame, File file, MySideBar sideBar){
        this.storage = storage;
        this.co = co;
        this.frame = frame;
        this.file = file;
        this.sideBar = sideBar;
    }

    public void importFiles(String username) {
        String uri = "mongodb+srv://dwng:abcd1234@lab7.edjw0nn.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase dataBase = mongoClient.getDatabase("Sayit");
            MongoCollection<Document> accountsCollection = dataBase.getCollection("Accounts");
            
            Document account = accountsCollection.find(eq("username", username)).first();
            ArrayList<Document> docList = (ArrayList) account.get("Commands");
            if(docList != null) {
                for (Document doc : docList) {
                    storage.addQuestion((String)doc.get("Command"), (String)doc.get("Response"));
                    JButton b = new JButton((String)doc.get("Command"));
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
            }
        }
    }

    /* Old import using local storage 
    public void importFiles(){
        try{
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            while (buf.ready()) {
                String[] split = buf.readLine().split("[;]");
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

    }*/
}
