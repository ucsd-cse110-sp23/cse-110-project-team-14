package sayit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadLoginInformation {
    private String username;
    private String password;
    File file = new File("login.txt");

    public boolean autoLoginEnabled(){
        System.out.println("Testing if autologin is enabled");
        if(file.exists() == false){
            System.out.println("File not found");
            return false;
        }
        else {
            try{
                System.out.println("Reading file");
                FileReader reader = new FileReader(file);
                BufferedReader buf = new BufferedReader(reader);
                String stringBoolean = buf.readLine();
                System.out.println("String Boolean: " + stringBoolean);
                if (stringBoolean.equals("true")){
                    System.out.println("Returning true");
                    return true;
                }

                else return false;
            }
            catch (Exception e){};
        }
        return false;
    }

    public String readUsername(){
        String username = null;

        if(file.exists() == false){
            return "No Username found";
        }

        try{
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            buf.readLine();
            username = buf.readLine();
            return username;
        }

        catch (Exception e){
            System.out.println("File not found.");
        }
        return username;

    }

    public String readPassword(){
        String password = null;

        if(file.exists() == false){
            return "No Username found";
        }

        try{
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            buf.readLine();
            buf.readLine();
            password = buf.readLine();
            return password;
        }

        catch (Exception e){
            System.out.println("File not found.");
        }
        return password;
    }

}
