import java.util.Objects;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ArticleCLI {
    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                throw new IllegalArgumentException("Error: Invalid parameter.");
            }
            String fileName = args[0];
            String command = args[1];
            File file = new File(fileName);
            ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
            ArticleManagement articleManagement = new ArticleManagement(articleDAO);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File " + fileName + " created");
            }
            switch (command) {
                case "add":
                    String articleType = args[2];
                    if (!args[3].matches("\\d+")) {
                        throw new IllegalArgumentException("Error: Invalid parameter.");
                    }
                    Integer id = Integer.valueOf(args[3]);
                    String title = args[4];
                    String publisher = args[5];
                    int releaseYear = 0;
                    if (!args[6].matches("\\d+")) {
                        throw new IllegalArgumentException("Error: Invalid parameter.");
                    }
                    releaseYear = Integer.parseInt(args[6]);
                    double basePrice = Double.parseDouble(args[7]);
                    if (Objects.equals(articleType, "book")) {
                        if (args.length != 9) {
                            throw new IllegalArgumentException("Error: Invalid parameter.");
                        }

                        Book book = new Book(id, title, releaseYear, publisher, basePrice, Integer.parseInt(args[8]));
                        articleManagement.saveArticle(book);
                        System.out.println("Info: Article " + id + " added.");

                    } else if (Objects.equals(articleType, "dvd")) {
                        if (args.length != 10) {
                            throw new IllegalArgumentException("Error: Invalid parameter.");
                        }
                        DVD dvd = new DVD(id, title, releaseYear, publisher, basePrice, Integer.parseInt(args[8]), Integer.parseInt(args[9]));
                        articleManagement.saveArticle(dvd);
                        System.out.println("Info: Article " + id + " added.");
                    } else {
                        throw new IllegalArgumentException("Error: Invalid parameter.");
                    }
                    break;
                case "list":
                    if (args.length == 3) {

                        if (articleManagement.getArticle(Integer.parseInt(args[2])) == null) {
                            throw new IllegalArgumentException("Error: Article not found. (id=" + Integer.parseInt(args[2]) + ")");
                        }
                        System.out.println(articleManagement.getArticle(Integer.parseInt(args[2])));

                    } else {
                        List<Article> articles = articleManagement.getAllArticles();
                        for (Article article : articles) {
                            System.out.println(article);
                        }
                    }
                    break;
                case "delete":
                    if (args.length != 3) {
                        throw new IllegalArgumentException("Error: Invalid parameter.");
                    }
                    if (articleManagement.getArticle(Integer.parseInt(args[2])) == null) {
                        throw new IllegalArgumentException("Error: Article not found. (id=" + Integer.parseInt(args[2]) + ")");
                    }
                    articleManagement.deleteArticle(Integer.parseInt(args[2]));
                    System.out.println("Info: Article " + args[2] + " deleted.");
                    break;
                case "count":
                    if (args.length == 3) {
                        if (Objects.equals(args[2], "book")) {
                            System.out.println(articleManagement.getNumberOfBooks());
                        } else if (Objects.equals(args[2], "dvd")) {
                            System.out.println(articleManagement.getNumberOfDVDs());
                        } else {
                            throw new IllegalArgumentException("Error: Invalid parameter.");
                        }
                    } else if (Objects.equals(args[1], "count")) {
                        System.out.println(articleManagement.getNumberOfArticles());
                    } else {
                        throw new IllegalArgumentException("Error: Invalid parameter.");
                    }
                    break;
                case "meanprice":
                    System.out.println(articleManagement.getMeanPrice());
                    break;
                case "oldest":
                    List<Integer> oldest = articleManagement.getOldestArticles();
                    for (Integer i : oldest) {
                        System.out.println("Id: " + i);
                    }
                    break;
                default:
                    System.out.println("Error: Invalid parameter.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Unknown Error: " + e.getMessage());
        }
    }
}