import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Label errorLabel;

    public void show() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Авторизация");
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DatabaseHandler.validateUser(username, password)) {
            errorLabel.setText("");
            new BudgetController(username).show();
            ((Stage) loginButton.getScene().getWindow()).close();
        } else {
            errorLabel.setText("Неверное имя пользователя или пароль");
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Заполните все поля");
            return;
        }

        if (DatabaseHandler.registerUser(username, password)) {
            errorLabel.setText("");
            new BudgetController(username).show();
            close();
        } else {
            errorLabel.setText("Пользователь уже существует");
        }
    }

    private void close() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}