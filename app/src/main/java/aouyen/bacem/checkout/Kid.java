package aouyen.bacem.checkout;

import java.util.ArrayList;

/**
 * Created by Bacem on 27/03/2018.
 */

public class Kid {
    private int age ;
    private  String classRoom ;
    private String fullName ;
    ArrayList<Mark> marks = new ArrayList<>() ;

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Mark> marks) {
        this.marks = marks;
    }

    public Kid(int age, String classRoom, String fullName, ArrayList<Mark> marks) {

        this.age = age;
        this.classRoom = classRoom;
        this.fullName = fullName;
        this.marks = marks;
    }

    public Kid() {
       super();

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
