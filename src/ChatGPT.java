import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

//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

public class ChatGPT {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";

    public static String askQuestion(String query) throws IOException, InterruptedException{
        // Set request parameters
        String prompt = query;
        int maxTokens = 100;

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);
        
        // Create HTTP Client
        HttpClient client = HttpClient.newHttpClient();

        // Create the request onject
        HttpRequest request = HttpRequest
         .newBuilder()
         .uri(URI.create(API_ENDPOINT))
         .header("Content-Type","application/json")
         .header("Authorization", String.format("Bearer %s", API_KEY))
         .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
         .build();
         
        // Send the request and recieve the response
        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );

        String responseBody = response.body();

        JSONObject responseJson = new JSONObject(responseBody);

        JSONArray choices = responseJson.getJSONArray("choices");
        String generateText = choices.getJSONObject(0).getString("text");

        return generateText;
    }
 
}
