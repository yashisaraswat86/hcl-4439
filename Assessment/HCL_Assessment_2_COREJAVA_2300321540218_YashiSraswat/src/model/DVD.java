package model;

public class DVD  extends  LibraryItem{
    int durationMinutes ;


    public DVD(int id, String title, int year, String isAvailable, int durationMinutes) {
        super(id, title, year, isAvailable);
        this.durationMinutes = durationMinutes;
    }

    @Override
    double lateFee(int daysLate){
        return 5.0 ;
    }

    @Override
    public String
    toString() {
        return "DVD{" +
                "durationMinutes=" + durationMinutes +
                '}';
    }
}
