package Lab3.src;

public class User {
    private String username;
    private String password;
    private boolean loggedIn; // Здесь можно было бы поменять на строку сессии с уникальным ключом
    private boolean isAdmin;

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.loggedIn = false;
        this.isAdmin = false;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        // Здесь можно добавить шифрование пароля, а также сохранение в БД
        this.password = password;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }
    public void setAdmin(boolean shouldBeAdmin) {
        this.isAdmin = shouldBeAdmin;
    }

    public boolean loggedIn() {
        return this.loggedIn;
    }

    public boolean login(String password) {
        if(!password.equals(this.getPassword())) return false;
        this.loggedIn = true;
        return true;
    }

    public void logout() {
        this.loggedIn = false;
    }
}
