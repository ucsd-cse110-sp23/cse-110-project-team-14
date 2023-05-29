package sayit;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.annotation.meta.Exhaustive;

public class RequestAccountHandler implements HttpHandler {
    
    
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Recieved";
        String method = httpExchange.getRequestMethod();
        try{
            if (method.equals("GET")){
                response = handleGet(httpExchange);
            }
            else{
                response = "Unimplemented HTTP Request Recieved";
            }
        }

        catch (Exception e){
            System.out.println("An error occured while trying to process your request");
            response = e.toString();
            e.printStackTrace();
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    };

    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = "Other error occured";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
          String nameAndPass = query.substring(query.indexOf("=") + 1);
          String username = nameAndPass.substring(0, nameAndPass.indexOf(",")); // get just the username
          String password = nameAndPass.substring(nameAndPass.indexOf(",") + 1);
          if(DBReadAccount.correctAccountPassword(username, password)) {
            response = "true";
          } 
          else if (DBReadAccount.doesAccountExist(username)) {
            response = "Incorrect password";
          } else {
            response = "No account with that username";
          }
        }
        return response;
    }

}
