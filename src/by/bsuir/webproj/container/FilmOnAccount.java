package by.bsuir.webproj.container;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class FilmOnAccount extends Entity {
    private Integer id;
    private String name;
    private Integer rating;
    private String comment;
    private String status;

    public FilmOnAccount() {

    }

    public FilmOnAccount(Integer id, String name, int rating, String comment, String status) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
