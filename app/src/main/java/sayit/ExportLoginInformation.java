package sayit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportLoginInformation {

    File file = new File("login.txt");

    public void exportLogin(String username, String password, boolean saveLogin) {
        System.out.println("Running export login");
        if (file.exists() == false) {
            try {
                file.createNewFile();
                System.out.println("File does not exist. Attempting to create a file to write.");
            } catch (Exception e) {
                System.out.println("Error while trying to create a file");
            }
        }

        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("File does not exist. Attempting to create a file to write.");
        }

        try {
            FileWriter writer = new FileWriter(file);
            if (saveLogin == true){
                writer.write("true\n");
            }
            else{
                writer.write("false\n");
            }

            writer.write(username + "\n");
            writer.write(password);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
