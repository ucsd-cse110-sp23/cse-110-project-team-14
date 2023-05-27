package sayit;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.annotation.meta.Exhaustive;

public class RequestHandler implements HttpHandler {

    Map <String,String> data;

    IChatBot ChatGPT = new ChatGPT();

    public RequestHandler(Map <String, String> data){
        this.data = data;
    }

    public RequestHandler(){}
    
    public String askQuestion(HttpExchange exchange){
        InputStream inputStream = exchange.getRequestBody();
        Scanner scanner = new Scanner(inputStream);
        String postData = scanner.nextLine();
        String response;
        try{
            response = ChatGPT.askQuestion(postData);
        }
        catch(Exception e){
            response = "Sorry. There was an error trying to process your response. Please try again";
        }
        scanner.close();
        return response;
    };

    /*To be implemented. Currently question history is stored locally.*/
    public void deleteQuestion(){};
    public void deleteQuestionHistory(){};
    public void saveQuestionHistory(){};
    public void loadQuestionHistory(){}

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Recieved";
        String method = httpExchange.getRequestMethod();

        try{
            if (method.equals("POST")){
                response = askQuestion(httpExchange);
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

        System.out.println("SERVER:RESPONSE FROM SERVER IS " + response);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    };


}
