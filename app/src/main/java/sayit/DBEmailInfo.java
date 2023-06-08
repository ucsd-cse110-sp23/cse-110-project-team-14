package sayit;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class DBEmailInfo {
    

    public static void saveInfo(String first, String last, String display, String email, String SMTP, String TLS, String password, String username) {
        String uri = "mongodb+srv://dwng:abcd1234@lab7.edjw0nn.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase dataBase = mongoClient.getDatabase("Sayit");
            MongoCollection<Document> accountsCollection = dataBase.getCollection("Accounts");
            
            Bson filter = eq("username", username);
            Bson updateOperation = set("Email Info", new Document("First",first)
                                            .append("Last", last)
                                            .append("Display", display)
                                            .append("Email", email)
                                            .append("SMTP", SMTP)
                                            .append("TLS", TLS)
                                            .append("password", password)
                                            );
            accountsCollection.updateOne(filter, updateOperation);
        }
    }

    /* Use for testing
    public static void main(String[] args) {
        saveInfo("Darren", "Ng", "ngwdarren", "dwng@ucsd.edu", "abcd", "abcd", "abcd1234", "test@test.com");
    }*/
}
