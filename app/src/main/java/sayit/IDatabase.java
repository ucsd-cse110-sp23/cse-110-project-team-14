package sayit;

public interface IDatabase {
    void addCommand(String command, String response, String username);
    void deleteCommand(String command, String username);
    void clearAll(String username);
       
}
