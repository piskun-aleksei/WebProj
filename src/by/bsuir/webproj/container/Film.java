package by.bsuir.webproj.container;

/**
 * Created by Алексей on 24.04.2016.
 */
public class Film extends Entity {
    private int id;
    private String name;
    private String desc;
    private int ageRating;
    private int price;

    public Film() {
    }

    public Film(String name, String desc, int ageRating, int price) {
        this.name = name;
        this.desc = desc;
        this.ageRating = ageRating;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }
}
