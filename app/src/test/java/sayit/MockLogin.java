package sayit;

public class MockLogin implements ILogin{
    
    public String login(String username, String password) {
        String toReturn = null;

        if(username.equals("dwng@ucsd.edu")) {
            if (password.equals("abcd1234")) {
                return "true";
            }
            return "Incorrect password";
        } else {
            toReturn =  "No account with that username";
        }

        if(username.equals("test@ucsd.edu")) {
            if (password.equals("abcd1234")) {
                return "true";
            }
            return "Incorrect password";
        } else {
            toReturn = "No account with that username";
        }

        return toReturn;
    }
}
