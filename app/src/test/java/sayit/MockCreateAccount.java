package sayit;

public class MockCreateAccount implements ICreate{
    
    public String create(String username, String password) {
        if(username.equals("dwng@ucsd.edu")) {
            return "Username taken";
        } else {
            return "Created Account";
        }
    }
}
