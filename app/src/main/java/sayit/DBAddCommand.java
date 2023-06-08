package sayit;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class DBAddCommand {

    public static void addCommand(String command, String response, String username) {
        String uri = "mongodb+srv://dwng:abcd1234@lab7.edjw0nn.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase dataBase = mongoClient.getDatabase("Sayit");
            MongoCollection<Document> accountsCollection = dataBase.getCollection("Accounts");
            
            Bson filter = eq("username", username);
            //Bson updateOperation = addToSet("Commands", "What is 2 + 2?$?=2 + 2 is 4.");
            Bson updateOperation = addToSet("Commands", new Document("Command",command).append("Response", response));
            accountsCollection.updateOne(filter, updateOperation);
            System.out.println("Created command " + username);
        }
    }
}
