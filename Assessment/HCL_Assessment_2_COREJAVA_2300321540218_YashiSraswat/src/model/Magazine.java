package model;

public class Magazine extends LibraryItem {
    String IssueNumber ;

    public Magazine(int id, String title, int year, String isAvailable, String issueNumber) {
        super(id, title, year, isAvailable);
        IssueNumber = issueNumber;
    }

    @Override
    double lateFee(int daysLate){
        return 5.0 ;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "IssueNumber='" + IssueNumber + '\'' +
                '}';
    }
}
