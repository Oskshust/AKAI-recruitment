package pl.akai.subclasses;

public class Author implements Comparable<Author> {
    private String name;
    private int howManyBooks;
    private double ratingSum;

    public Author(String name) {
        this.name = name;
        this.howManyBooks = 0;
        this.ratingSum = 0;
    }

    public void update(double rating){
        this.setHowManyBooks(this.getHowManyBooks() + 1);
        this.setRatingSum(this.getRatingSum() + rating);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowManyBooks() {
        return howManyBooks;
    }

    public void setHowManyBooks(int howManyBooks) {
        this.howManyBooks = howManyBooks;
    }

    public double getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(double ratingSum) {
        this.ratingSum = ratingSum;
    }

    @Override
    public int compareTo(Author o) {
        return Double.compare((this.getRatingSum()/this.getHowManyBooks()), (o.getRatingSum()/o.getHowManyBooks()));
    }
}
