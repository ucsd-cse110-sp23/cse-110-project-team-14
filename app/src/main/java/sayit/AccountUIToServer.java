package sayit;

public class AccountUIToServer {
    private String username;
    private String password;
    private ICreate accCreate;
    private ILogin accLogin;

    public AccountUIToServer(ILogin l, ICreate c) {
        accCreate = c;
        accLogin = l;
    }

    public String createAccount(String username, String password, String checkPass) {
        if(username.indexOf(",") != -1) {
            return "username can not contain commas";
        }
        if(!password.equals(checkPass)) {
            return "passwords do not match";
        }
        String response = accCreate.create(username, password);
        if(response.equals("true")) {
            this.username = username;
            this.password = password;
        }
        return response;
    }

    /**
     * attempts to login with info, if success, then saves user info
     * @param username 
     * @param password
     * @return "true" if succesful login, error message otherwise
     */
    public String login(String username, String password) {
        String response = accLogin.login(username, password);
        if(response.equals("true")) {
            this.username = username;
            this.password = password;
        }
        return response;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    /**
     * ONLY TO BE USED FOR TESTING
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
