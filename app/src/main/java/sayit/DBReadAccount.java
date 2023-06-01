package sayit;

import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;


public class DBReadAccount {
    
    public static boolean doesAccountExist(String username) {
        String uri = "mongodb+srv://dwng:abcd1234@lab7.edjw0nn.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase dataBase = mongoClient.getDatabase("Sayit");
            MongoCollection<Document> accountsCollection = dataBase.getCollection("Accounts");

            // find one document with Filters.eq()
            Document account = accountsCollection.find(eq("username", username)).first();
            if(account == null) {
                return false;
            } else {
                return true;
            }
            
        }
    }

    public static boolean correctAccountPassword(String username, String password) {
        String uri = "mongodb+srv://dwng:abcd1234@lab7.edjw0nn.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase dataBase = mongoClient.getDatabase("Sayit");
            MongoCollection<Document> accountsCollection = dataBase.getCollection("Accounts");

            // find one document with Filters.eq()
            Document account = accountsCollection.find(eq("username", username)).first();
            if(account == null) {
                return false;
            } else {
                String cPassword = (String) account.get("password"); // correct password
                return cPassword.equals(password);
            }
            
        }
    }
}
