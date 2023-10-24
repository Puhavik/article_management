import java.io.Serializable;
import java.time.Year;
import java.text.DecimalFormat;

abstract public class Article implements Serializable {
    private Integer id;
    private String title;
    private int releaseYear;
    private String publisher;
    private double basePrice;

    public Article(Integer id, String title, int releaseYear, String publisher, double basePrice) {
        Year year = Year.now();
        int currentYear = year.getValue();

        if (releaseYear > currentYear || releaseYear < 1) {
            throw new IllegalArgumentException("Error: Invalid release year.");
        }
        if (basePrice < 0) {
            throw new IllegalArgumentException("Error: Invalid Price");
        }
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Error: Title is Empty");
        }
        if (id < 1) {
            throw new IllegalArgumentException("Error: Invalid ID");
        }
        if (publisher.isEmpty()) {
            throw new IllegalArgumentException("Error: Publisher is Empty");
        }

        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.publisher = publisher;
        this.basePrice = basePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getAge() {
        Year year = Year.now();
        int currentYear = year.getValue();
        return currentYear - releaseYear;
    }

    public double getPrice() {
        double retPrice = basePrice * ((100 - getDiscount()) / 100);
        DecimalFormat df = new DecimalFormat("#.##");
        String roundedValue = df.format(retPrice);
        return Double.parseDouble(roundedValue);
    }

    public abstract double getDiscount();
}