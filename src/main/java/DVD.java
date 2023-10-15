import java.io.Serializable;

public class DVD extends Article implements Serializable {
    int duration;
    int ageRate;

    public DVD(Integer id, String title, int releaseYear, String publisher, double basePrice, int duration, int ageRate) {
        super(id, title, releaseYear, publisher, basePrice);
        if (duration < 0) {
            throw new IllegalArgumentException("Error: Invalid Duration");
        }
        if (!(ageRate == 0 || ageRate == 6 || ageRate == 12 || ageRate == 16 || ageRate == 18)) {
            throw new IllegalArgumentException("Error: Invalid Age Rating");
        }
        this.duration = duration;
        this.ageRate = ageRate;
    }

    @Override
    public double getDiscount() {
        return switch (ageRate) {
            case 0 -> 20;
            case 6 -> 15;
            case 12 -> 10;
            case 16 -> 5;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "Type:       " + "DVD" + "\n" +
                "ID:         " + getId() + "\n" +
                "Title:      " + getTitle() + "\n" +
                "Year:       " + getReleaseYear() + "\n" +
                "Publisher:  " + getPublisher() + "\n" +
                "Base Price: " + getBasePrice() + "\n" +
                "Price:      " + getPrice() + "\n" +
                "Length:     " + duration + "\n" +
                "Age rating: " + ageRate;
    }

}
