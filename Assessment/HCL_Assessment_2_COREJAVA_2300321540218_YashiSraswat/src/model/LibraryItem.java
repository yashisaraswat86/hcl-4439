package model;

import java.util.Comparator;
import java.util.Objects;

abstract class LibraryItem implements Comparable<LibraryItem> {
    private int id ;
    private String title ;
    private int year ;
    private String IsAvailable ;

    public LibraryItem(int id, String title, int year, String isAvailable) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.IsAvailable = isAvailable;
        if(title.isEmpty()){
            System.out.println("Invalid Title");
        }
        if(year < 1900 || year > 2026){
            System.out.println("Invalid Year");
        }
    }

    abstract  double latefee(int daysLate);

    @Override
    public int compareTo(LibraryItem i){
        return (this.title).compareTo(i.title);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LibraryItem that = (LibraryItem) o;
        return id == that.id && year == that.year && Objects.equals(title, that.title) && Objects.equals(IsAvailable, that.IsAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, IsAvailable);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getIsAvailable() {
        return IsAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIsAvailable(String isAvailable) {
        this.IsAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", IsAvailable='" + IsAvailable + '\'' +
                '}';
    }
}
