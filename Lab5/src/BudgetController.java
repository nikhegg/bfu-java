import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BudgetController {
    @FXML private Label welcomeLabel;
    @FXML private TableView<Transaction> transactionsTable;
    @FXML private TableColumn<Transaction, String> userColumn;
    @FXML private TableColumn<Transaction, String> typeColumn;
    @FXML private TableColumn<Transaction, Double> amountColumn;
    @FXML private TableColumn<Transaction, String> categoryColumn;
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML private TableColumn<Transaction, String> descriptionColumn;

    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> typeChoiceBox;
    @FXML private ChoiceBox<String> categoryChoiceBox;
    @FXML private DatePicker datePicker;
    @FXML private TextField descriptionField;
    @FXML private Button addButton;
    @FXML private Label balanceLabel;

    private String username;
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    public BudgetController(String username) {
        this.username = username;
    }

    public void show() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/budget.fxml"));
            loader.setController(this);

            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Семейный бюджет");
            stage.setResizable(true);

            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        welcomeLabel.setText("Добро пожаловать, " + username + "!");

        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        typeChoiceBox.getItems().addAll("Доход", "Расход");
        typeChoiceBox.setValue("Расход");

        categoryChoiceBox.getItems().addAll("Продукты", "Транспорт", "Жилье", "Развлечения", "Здоровье", "Одежда", "Другое");
        categoryChoiceBox.setValue("Продукты");

        datePicker.setValue(LocalDate.now());

        addButton.setOnAction(e -> addTransaction());

        setupTableContextMenu();

        loadTransactions();
        updateBalance();
    }

    private void setupTableContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem copyAmountItem = new MenuItem("Скопировать сумму");
        copyAmountItem.setOnAction(e -> copyAmountToClipboard());

        MenuItem copyRowItem = new MenuItem("Скопировать строку");
        copyRowItem.setOnAction(e -> copyRowToClipboard());

        MenuItem deleteItem = new MenuItem("Удалить");
        deleteItem.setOnAction(e -> deleteSelectedTransaction());

        contextMenu.getItems().addAll(copyAmountItem, copyRowItem, deleteItem);

        transactionsTable.setRowFactory(tv -> {
            TableRow<Transaction> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });

            return row;
        });
    }

    private void copyAmountToClipboard() {
        Transaction selected = transactionsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(selected.getAmount()));
            clipboard.setContent(content);
        }
    }

    private void copyRowToClipboard() {
        Transaction selected = transactionsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String formattedDate = selected.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String rowText = String.format("%s: %s от %s - %.2f (%s)",
                    formattedDate,
                    selected.getType(),
                    selected.getUsername(),
                    selected.getAmount(),
                    selected.getDescription() != null ? selected.getDescription() : "");

            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(rowText);
            clipboard.setContent(content);
        }
    }

    private void deleteSelectedTransaction() {
        Transaction selected = transactionsTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (!selected.getUsername().equals(username)) {
            showAlert("Ошибка",
                    String.format("Вы не можете удалять чужие записи. Попросите пользователя %s удалить эту запись",
                            selected.getUsername()));
            return;
        }

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Подтверждение удаления");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText("Вы уверены, что хотите удалить запись?");

        ButtonType deleteButton = new ButtonType("Удалить", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Оставить", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationDialog.getButtonTypes().setAll(deleteButton, cancelButton);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
            if (buttonType == deleteButton) {
                if (DatabaseHandler.deleteTransaction(selected)) {
                    transactions.remove(selected);
                    updateBalance();
                    showAlert("Успех", "Запись успешно удалена");
                } else {
                    showAlert("Ошибка", "Не удалось удалить транзакцию");
                }
            }
        });
    }

    private void loadTransactions() {
        transactions.clear();
        transactions.addAll(DatabaseHandler.getAllTransactions());
        transactionsTable.setItems(transactions);
    }

    private void addTransaction() {
        try {
            String type = typeChoiceBox.getValue();
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryChoiceBox.getValue();
            LocalDate date = datePicker.getValue();
            String description = descriptionField.getText();

            Transaction transaction = new Transaction(username, type, amount, category, date, description);

            if (DatabaseHandler.addTransaction(transaction)) {
                transactions.add(transaction);
                updateBalance();
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите корректную сумму");
        }
    }

    private void updateBalance() {
        double balance = DatabaseHandler.calculateTotalBalance();
        balanceLabel.setText(String.format("Бюджет семьи: %.2f ₽", balance));
    }

    private void clearFields() {
        amountField.clear();
        descriptionField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}