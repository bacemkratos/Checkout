package aouyen.bacem.checkout;

/**
 * Created by Bacem on 02/04/2018.
 */

public class Mark {
    int type ;
    int   mark ;
    String note ;
    int semester ;
    String subject ;

    public Mark(int type, int mark, String note, int semester, String subject) {
        this.type = type;
        this.mark = mark;
        this.note = note;
        this.semester = semester;
        this.subject = subject;
    }

    public Mark() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
