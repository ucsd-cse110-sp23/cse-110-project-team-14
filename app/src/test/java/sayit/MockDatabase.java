package sayit;

import java.util.HashMap;

public class MockDatabase implements IDatabase{
    private HashMap<String, String> map;

    public MockDatabase() {
        map = new HashMap<String, String>();
    }

    @Override
    public void addCommand(String command, String response, String username) {
        map.put(command, response);
    }

    @Override
    public void deleteCommand(String command, String username) {
        map.remove(command);
    }

    @Override
    public void clearAll(String username) {
        map = new HashMap<String, String>();
    }
    
    public HashMap<String, String> getStorage() {
        return map;
    }
    
}
