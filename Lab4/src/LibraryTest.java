package Lab4.src;

import java.util.List;

public class LibraryTest {
    private Library library;

    public LibraryTest() {
        this.library = new Library();
    }

    public void launch() {
        this.library.addBook(new Book("Война и Мир", "Лев Толстой", 1867));
        this.library.addBook(new Book("Муму", "Иван Тургенев", 1852));
        this.library.addBook(new Book("Тарас Бульба", "Николай Гоголь", 1835));
        this.library.addBook(new Book("Мёртвые души", "Николай Гоголь", 1842));
        this.library.addBook(new Book("Детство", "Лев Толстой", 1852));
        
        System.out.println("--- ТЕСТ ВЫВОДА С ЛИШНЕЙ КНИГОЙ ---");
        Book testBook = new Book("TEST", "No author", 1952);
        this.library.addBook(testBook);
        this.library.printAllBooks();
        this.library.printUniqueAuthors();
        this.library.removeBook(testBook);
        System.out.println("--------\n");



        this.library.printAllBooks();
        System.out.println();
        this.library.printUniqueAuthors();
        System.out.println();
        this.library.printAuthorStatistics();
        System.out.println();

        System.out.println("Все книги автора Николай Гоголь:");
        List<Book> gogolBooks = this.library.findBooksByAuthor("Николай Гоголь");
        gogolBooks.forEach(book -> {
            System.out.println(String.format("- %s (%s)", book.getTitle(), book.getYear()));
        });
        System.out.println();

        System.out.println("Все книги, выпущенные в 1852 году:");
        List<Book> booksByYear = this.library.findBooksByYear(1852);
        booksByYear.forEach(book -> {
            System.out.println(String.format("- %s", book.toString()));
        });
    }
}
