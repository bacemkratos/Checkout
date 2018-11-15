package aouyen.bacem.checkout;

/**
 * Created by Bacem on 18/02/2018.
 */

public class users {
private String name;
private String prenom ;
private String gender ;


    public users() {

    }

    public users(String name, String prenom, String gender) {
        this.name = name;
        this.prenom = prenom;
        this.gender = gender;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
