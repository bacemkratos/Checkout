package aouyen.bacem.checkout;

import java.util.ArrayList;

/**
 * Created by Bacem on 28/03/2018.
 */

public class News {
    ArrayList<String> tags ;

    String title ;
    int type ;
    String description ;

    public News() {
    }

    public News(ArrayList<String> tags, String title, int type, String description) {
        this.tags = tags;
        this.title = title;
        this.type = type;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<String> getTags() {

        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
