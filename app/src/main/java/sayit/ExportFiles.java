package sayit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ExportFiles {
    Storage storage;
    File file;

    ExportFiles(Storage storage, File file) {
        this.storage = storage;
        this.file = file;
    }

    /**
     * The function exports data from questionhistory to a file in CSV format. - Generated by Mintlify
     */
    public void exportFile() {
        System.out.println("Running export files");
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
            for (HashMap.Entry<String, String> entry : storage.getHashMap().entrySet()) {
                String question = entry.getKey();
                String answer = entry.getValue().replace("\n", "");
                String toReturn = question + "," + answer + "\n";
                writer.write(toReturn);
                System.out.println("Writing to file");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
