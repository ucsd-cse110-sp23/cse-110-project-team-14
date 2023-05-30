package sayit;

public class MockLogin implements ILogin{
    
    public String login(String username, String password) {
        if(username.equals("dwng@ucsd.edu")) {
            if (password.equals("abcd1234")) {
                return "true";
            }
            return "Incorrect password";
        } else {
            return "No account with that username";
        }
    }
}
