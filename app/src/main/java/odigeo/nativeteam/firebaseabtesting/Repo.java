package odigeo.nativeteam.firebaseabtesting;

/**
 * Created by daniel.morales on 14/11/16.
 */
public class Repo {
    long id;
    String name;
    String url;
    String description;
    String createdAt;
    String language;

    public Repo(long id, String name, String url, String description, String createdAt, String language) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.createdAt = createdAt;
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLanguage() {
        return language;
    }
}
