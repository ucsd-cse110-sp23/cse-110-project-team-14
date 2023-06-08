/**
 * The ReadLoginInformation class reads login information from a file and provides
 * methods to check if auto-login is enabled, read the username, and read the
 * password.
 * Documentation generated by Mintlify
 */
package sayit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadLoginInformation {
    File file = new File("login.txt");

   /**
    * This Java function checks if auto-login is enabled by reading a file and
    * returning a boolean value.
    * 
    * @return The method returns a boolean value, either true or false, depending
    * on whether autologin is enabled or not.
    * Documentation generated by Mintlify
    */
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

    /**
     * This function reads a username from a file and returns it, or returns "No
     * Username found" if the file does not exist.
     * 
     * @return If the file does not exist, the method returns the string "No
     * Username found". If the file exists and the username is successfully read
     * from the file, the method returns the username as a string. If there is an
     * exception while reading the file, the method prints "File not found" and
     * returns null.
     *  Documentation generated by Mintlify
     */
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

    /**
     * This function reads a password from a file and returns it, or returns an
     * error message if the file is not found.
     * 
     * @return If the file does not exist, the method returns the string "No
     * Password found". If the file exists and the password is successfully read
     * from it, the method returns the password string. If there is an exception
     * while reading the file, the method prints "File not found" and returns the
     * password string, which is null.
     * Documentation generated by Mintlify
     */
    public String readPassword(){
        String password = null;

        if(file.exists() == false){
            return "No Password found";
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
