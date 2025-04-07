package Lab4.src;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", this.author, this.title, this.year);
    }

    @Override
    public boolean equals(Object book) {
        if(book == null || this.getClass() != book.getClass()) return false;
        if(this == book) return true;
        Book comp = (Book) book;
        return (
            this.title.equals(comp.getTitle()) &&
            this.author.equals(comp.getAuthor()) &&
            this.year == comp.getYear()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.author, this.year);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
