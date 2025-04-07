package Lab4.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Library {
    private List<Book> books;
    private Set<String> authors;
    private Map<String, Integer> stats;

    public Library() {
        this.books = new ArrayList<Book>();
        this.authors = new HashSet<String>();
        this.stats = new HashMap<String, Integer>();
    }

    public void addBook(Book book) {
        this.books.add(book);
        String author = book.getAuthor();
        if(!this.authors.contains(author)) this.authors.add(author);
        if(!this.stats.containsKey(author)) {
            this.stats.put(author, 1);
        } else {
            int currentAmount = this.stats.get(author);
            this.stats.replace(author, currentAmount + 1);
        }
    }

    public void removeBook(Book book) {
        String author = book.getAuthor();
        this.books.remove(book);
        if(this.authors.contains(author)) this.authors.remove(author);
        if(!this.stats.containsKey(author)) return;
        int currentAmount = this.stats.get(author);
        if(currentAmount - 1 <= 0) this.stats.remove(author);
        else this.stats.replace(author, currentAmount - 1);
    }

    public List<Book> findBooksByAuthor(String author) {
        ArrayList<Book> booksCache = new ArrayList<Book>();
        for(Book book : this.books) {
            if(book.getAuthor().equals(author)) booksCache.add(book);
        }
        return booksCache;
    }

    public List<Book> findBooksByYear(int year) {
        ArrayList<Book> booksCache = new ArrayList<Book>();
        for(Book book : this.books) {
            if(book.getYear() == year) booksCache.add(book);
        }
        return booksCache;
    }

    public void printAllBooks() {
        System.out.println("Список книг:");
        this.books.forEach(book -> {
            System.out.println(String.format("- %s", book.toString()));
        });
    }

    public void printUniqueAuthors() {
        System.out.println("Добавленные авторы:");
        this.authors.forEach(author -> {
            System.out.println(String.format("- %s", author));
        });
    }

    public void printAuthorStatistics() {
        System.out.println("Статистика по авторам:");
        this.stats.forEach((author, booksAmount) -> {
            System.out.println(String.format("- %s - %s кн.", author, booksAmount));
        });
    }
}
