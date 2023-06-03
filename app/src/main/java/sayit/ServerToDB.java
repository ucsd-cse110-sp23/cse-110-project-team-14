package sayit;

public class ServerToDB implements IDatabase{

    @Override
    public void addCommand(String command, String response, String username) {
        DBAddCommand.addCommand(command, response, username);
    }

    @Override
    public void deleteCommand(String command, String username) {
        DBDeleteCommand.deleteCommand(command, username);
    }

    @Override
    public void clearAll(String username) {
       DBClearAll.clearAll(username);
    }
    
}
