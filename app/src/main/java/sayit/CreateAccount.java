package sayit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.*;

/**
 * Class to handle create account, doesn't interface w/ DB
 * Use 
 */
public class CreateAccount {
    static final String URL = "http://localhost:8100/account";

    /**
     * 
     * @param username username to check
     * @param password password to check
     * @return "true" if both are in db, "No account with that username" if no account, "Incorrect password" for wrong password
     */
    public static String create(String username, String password) {

        String response = "HTTP REQUEST SENT";
        System.out.println(response);
        try {
            URL url = new URL(URL + "?=" + username + "," + password);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(false);
            
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );


            response = in.readLine();
            in.close();
            
            System.out.println(response);

        }catch (MalformedURLException exception){
            exception.printStackTrace();
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return response;
    }
    /* // Use for testing purposes only
    public static void main(String[] args) {
        try {
            MyServer server = new MyServer();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        System.out.println(CreateAccount.create("abcd@ucsd.edu", "abcd1234"));
    }*/
}
