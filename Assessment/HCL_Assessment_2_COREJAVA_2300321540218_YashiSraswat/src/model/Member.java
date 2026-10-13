package model;

public class Member {
    int id ;
    String name ;
    String mail ;
    List<Borrowable> borrowableList ;

    public Member(int id, String name, String mail, List<Borrowable> borrowableList) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.borrowableList = borrowableList;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", borrowableList=" + borrowableList +
                '}';
    }
}
