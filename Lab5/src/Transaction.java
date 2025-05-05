import java.util.Objects;
import java.time.LocalDate;

public class Transaction {
    private String username;
    private String type;
    private double amount;
    private String category;
    private LocalDate date;
    private String description;

    public Transaction(String username, String type, double amount, String category, LocalDate date, String description) {
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public String getUsername() { return username; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(username, that.username) &&
                Objects.equals(type, that.type) &&
                Objects.equals(category, that.category) &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, type, amount, category, date, description);
    }
}