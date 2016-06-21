package by.bsuir.webproj.container;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class Commentary extends Entity {
    private int id;
    private int pid;
    private int fid;
    private String filmComment;
    private int rating;
    private String date;
    private String time;
    private String personName;

    public Commentary() {

    }

    public Commentary(int id, int pid, int fid, String filmComment, int rating, String date, String time) {
        this.id = id;
        this.pid = pid;
        this.fid = fid;
        this.filmComment = filmComment;
        this.rating = rating;
        this.date = date;
        this.time = time;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFilmComment() {
        return filmComment;
    }

    public void setFilmComment(String filmComment) {
        this.filmComment = filmComment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
