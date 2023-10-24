import java.io.Serializable;

public class Book extends Article implements Serializable {
    int numberOfPages;

    public Book(Integer id, String title, int releaseYear, String publisher, double basePrice, int numberOfPages) {
        super(id, title, releaseYear, publisher, basePrice);
        if (numberOfPages < 0) {
            throw new IllegalArgumentException("Error: Invalid Number of Pages");
        }
        this.numberOfPages = numberOfPages;
    }

    @Override
    public double getDiscount() {
        double setDiscount = getAge() * 5;
        if (setDiscount > 30) {
            setDiscount = 30;
        }
        if (numberOfPages > 1000) {
            setDiscount = setDiscount + 3;
        }
        return setDiscount;
    }

    @Override
    public String toString() {
        return "Type:       " + "Book" + "\n" +
                "Id:         " + getId() + "\n" +
                "Title:      " + getTitle() + "\n" +
                "Year:       " + getReleaseYear() + "\n" +
                "Publisher:  " + getPublisher() + "\n" +
                "Base price: " + getBasePrice() + "\n" +
                "Price:      " + getPrice() + "\n" +
                "Pages:      " + numberOfPages + "\n";
    }
}