package aouyen.bacem.checkout;

import java.util.ArrayList;

/**
 * Created by Bacem on 31/03/2018.
 */

public class ClassRoom {
  private   ArrayList<Subject> subjects = new ArrayList<>();

   public ClassRoom()
   {
       super();
   }
    public ClassRoom(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
