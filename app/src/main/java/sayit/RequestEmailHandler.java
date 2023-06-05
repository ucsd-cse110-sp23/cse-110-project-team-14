package sayit;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class RequestEmailHandler implements HttpHandler {
    AccountUIToServer connecter;
    
    
    public RequestEmailHandler(AccountUIToServer connect) {
        connecter = connect;
    }
    
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Recieved";
        String method = httpExchange.getRequestMethod();
        try{
            if (method.equals("GET")){
              response = handleGet(httpExchange);
            } else if (method.equals("POST")) {
              response = handlePost(httpExchange);
            } else {
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
    }

    public String handleGet(HttpExchange exchange) {
        return DBReadEmail.getInfo(connecter.getUsername());
    }

    public String handlePost(HttpExchange exchange){
        InputStream inputStream = exchange.getRequestBody();
        Scanner scanner = new Scanner(inputStream);
        String postData = scanner.nextLine();
        String first = postData.substring(0, postData.indexOf(","));
        postData = postData.substring(postData.indexOf(",") + 1);
        String last = postData.substring(0, postData.indexOf(","));
        postData = postData.substring(postData.indexOf(",") + 1);
        String display = postData.substring(0, postData.indexOf(","));
        postData = postData.substring(postData.indexOf(",") + 1);
        String email = postData.substring(0, postData.indexOf(","));
        postData = postData.substring(postData.indexOf(",") + 1);
        String smtp = postData.substring(0, postData.indexOf(","));
        postData = postData.substring(postData.indexOf(",") + 1);
        String tls = postData.substring(0, postData.indexOf(","));
        postData = postData.substring(postData.indexOf(",") + 1);
        String password = postData.substring(0, postData.indexOf(","));
        DBEmailInfo.saveInfo(first, last, display, email, smtp, tls, password, connecter.getUsername());
        scanner.close();

        String response = "Sent info";
        
        return response;
    }
}
