package sayit;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;


import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
public class DBAddCommand {
    // "$?=" is the delimeter for between a question and an answer
    public static void main(String[] args) {
        String uri = "mongodb+srv://dwng:abcd1234@lab7.edjw0nn.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase dataBase = mongoClient.getDatabase("Sayit");
            MongoCollection<Document> accountsCollection = dataBase.getCollection("Accounts");
            
            Bson filter = eq("username", "test@test.com");
            //Bson updateOperation = addToSet("Commands", "What is 2 + 2?$?=2 + 2 is 4.");
            Bson updateOperation = addToSet("Commands", new Document("Command","What is 1 + 1?").append("Response", "1 + 1 is 2."));
            accountsCollection.updateOne(filter, updateOperation);
            System.out.println("Updated dwng@ucsd.edu");
        }
    }
}
