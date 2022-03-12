package pl.akai.subclasses;

public class Book{
    private String id;
    private String title;
    private String author;
    private double rating;

    public Book(String id, String title, String author, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
