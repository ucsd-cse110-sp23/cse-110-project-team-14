package sayit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EmailConnecter {
    
    final String URL = "http://localhost:8100/email";
    private String first = "";
    private String last = "";
    private String display = "";
    private String email = "";
    private String SMTP = "";
    private String TLS = "";
    private String password = "";

    public void setup(String first, String last, String display, String email, String SMTP, String TLS, String password) {
        String response = "HTTP REQUEST SENT";

        
        try{
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(first + "," + last + "," + display + "," + email + "," + SMTP + "," + TLS + "," + password + ",");
            out.flush();
            out.close();


            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            response = in.readLine();
            System.out.println(response);
            in.close();
            

        }catch (MalformedURLException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }catch (IOException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
        
    }

    public void getEmailInfo() {
        String response = "HTTP REQUEST SENT";

        try{
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            response = in.readLine();
            if(!response.equals("false")) {
                updateVal(response);
            }
            
            

        }catch (MalformedURLException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }catch (IOException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }

        System.out.println(response);
    }

    public void updateVal(String input) {
        String temp = input;
            
        first = temp.substring(temp.indexOf("First=") + 6, temp.indexOf(","));
        temp = temp.substring(temp.indexOf(",") + 1);
        
        last = temp.substring(temp.indexOf("Last=") + 5, temp.indexOf(","));
        temp = temp.substring(temp.indexOf(",") + 1);

        display = temp.substring(temp.indexOf("Display=") + 8, temp.indexOf(","));
        temp = temp.substring(temp.indexOf(",") + 1);

        email = temp.substring(temp.indexOf("Email=") + 6, temp.indexOf(","));
        temp = temp.substring(temp.indexOf(",") + 1);
            
        SMTP = temp.substring(temp.indexOf("SMTP=") + 5, temp.indexOf(","));
        temp = temp.substring(temp.indexOf(",") + 1);
            
        TLS = temp.substring(temp.indexOf("TLS=") + 4, temp.indexOf(","));
        temp = temp.substring(temp.indexOf(",") + 1);

        password = temp.substring(temp.indexOf("password=") + 9, temp.indexOf("}"));
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getDisplay() {
        return display;
    }

    public String getEmail() {
        return email;
    }

    public String getSMTP() {
        return SMTP;
    }

    public String getTLS() {
        return TLS;
    }

    public String getPassword() {
        return password;
    }

}
