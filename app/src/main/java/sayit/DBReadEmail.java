package sayit;

import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

public class DBReadEmail {
    public static String getInfo(String username) {
        String uri = "mongodb+srv://dwng:abcd1234@lab7.edjw0nn.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase dataBase = mongoClient.getDatabase("Sayit");
            MongoCollection<Document> accountsCollection = dataBase.getCollection("Accounts");

            // find one document with Filters.eq()
            Document account = accountsCollection.find(eq("username", username)).first();
            if(account.get("Email Info") == null) {
                return "false";
            }
            return account.get("Email Info").toString();
        }
    }

    /* Use for testing
    public static void main(String[] args) {
        System.out.print(getInfo("test@test.com"));
    } */
}
