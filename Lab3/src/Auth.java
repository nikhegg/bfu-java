package Lab3.src;

import java.util.ArrayList;



public class Auth {
    ArrayList<User> users;

    public Auth() {
        this.users = new ArrayList<User>();
    }

    public User registerUser(String username, String password) throws Exception {
        if(this.userExists(username)) {
            StringBuilder err = new StringBuilder();
            err.append("User with username ");
            err.append(username);
            err.append(" already exists");
            String error = err.toString();
            throw new Exception(error);
        }
        User newUser = new User(username, password);
        this.users.add(newUser);

        // Абсолютно небезопасно, но нужно повысить юзера до админа
        return newUser;
    }

    private User findUser(String username) throws Exception {
        for(int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            if(username.equals(user.getUsername())) return user;
        }
        throw new Exception("User not found");
    }

    public boolean userExists(String username) {
        for(int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            if(username.equals(user.getUsername())) return true;
        }
        return false;
    }

    public boolean isAdmin(String username) throws Exception {
        try {
            User user = this.findUser(username);
            return user.isAdmin();
        } catch (Exception e) {
            throw e;
        }
    }

    public void login(String username, String password) throws Exception {
        try {
            User user = this.findUser(username);
            boolean loggedIn = user.login(password);
            if(!loggedIn) throw new Exception("Incorrect password");
        } catch (Exception e) {
            throw e;
        }
    }

    public void logout(String username) {

    }

}
