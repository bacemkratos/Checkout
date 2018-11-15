package aouyen.bacem.checkout;

import java.util.ArrayList;

/**
 * Created by Bacem on 31/03/2018.
 */

public class Subject {
    private String name ;
    private String description;
    private String currentModule;
    private int completation ;
    private String teacher ;


    public Subject() {
    }

    public Subject(String name, String description, String currentModule, int completation, String teacher) {
        this.name = name;
        this.description = description;
        this.currentModule = currentModule;
        this.completation = completation;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentModule() {
        return currentModule;
    }

    public void setCurrentModule(String currentModule) {
        this.currentModule = currentModule;
    }

    public int getCompletation() {
        return completation;
    }

    public void setCompletation(int completation) {
        this.completation = completation;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
