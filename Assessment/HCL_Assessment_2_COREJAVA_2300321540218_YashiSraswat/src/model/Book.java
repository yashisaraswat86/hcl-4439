package model;

public class Book extends LibraryItem{
    String author ;
    String ISBN ;

    public Book(int id, String title, int year, String isAvailable, String author) {
        super(id, title, year, isAvailable);
        this.author = author;
    }

    @Override
    double lateFee(int daysLate){
        return 2.0 ;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
