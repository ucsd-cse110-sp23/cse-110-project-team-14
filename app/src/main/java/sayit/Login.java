package sayit;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.*;


public class Login {
    static final String URL = "http://localhost:8100/account";

    /**
     * 
     * @param username username to check
     * @param password password to check
     * @return "true" if both are in db, "No account with that username" if no account, "Incorrect password" for wrong password
     */
    public static String login(String username, String password) {

        String response = "HTTP REQUEST SENT";
        System.out.println(response);
        try {
            URL url = new URL(URL + "?=" + username + "," + password);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
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
    /*  Use for testing purposes only
    public static void main(String[] args) {
        try {
            MyServer server = new MyServer();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        //System.out.println(Login.login("dwng@ucsd.edu", "abcd1234"));
    }*/
}
