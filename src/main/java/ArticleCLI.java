import java.util.Objects;
import java.io.File;
import java.io.IOException;


public class ArticleCLI {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Command is Invalid: java ArticleCLI <file> <command>");
            return;
        }

        String fileName = args[0];
        String command = args[1];

        File file = new File(fileName);

        ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
        ArticleManagement articleManagement = new ArticleManagement(articleDAO);

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File " + fileName + " created");
            }
            switch (command) {
                case "add":
                    String articleType = args[2];
                    Integer id = Integer.valueOf(args[3]);
                    String title = args[4];
                    String publisher = args[5];
                    int releaseYear = 0;
                    try {
                        releaseYear = Integer.parseInt(args[6]);
                    } catch (Exception e) {
                        System.err.println("Error: Invalid Parameter. " + e.getMessage());
                    }
                    double basePrice = Double.parseDouble(args[7]);
                    if (Objects.equals(articleType, "book")) {
                        if (args.length != 9) {
                            throw new IllegalArgumentException("Error: Invalid Parameter");
                        }
                        Book book = new Book(id, title, releaseYear, publisher, basePrice, Integer.parseInt(args[8]));
                        articleManagement.saveArticle(book);
                        System.out.println("Article " + id + " added");
                    } else if (Objects.equals(articleType, "dvd")) {
                        if (args.length != 10) {
                            throw new IllegalArgumentException("Error: Invalid Parameter");
                        }
                        DVD dvd = new DVD(id, title, releaseYear, publisher, basePrice, Integer.parseInt(args[8]), Integer.parseInt(args[9]));
                        articleManagement.saveArticle(dvd);
                        System.out.println("Article " + id + " added");
                    } else {
                        throw new IllegalArgumentException("Error: Invalid Article Type");
                    }
                    break;
                case "list":
                    if (args.length == 3) {
                        if (articleManagement.getArticle(Integer.parseInt(args[2])) == null) {
                            throw new IllegalArgumentException("Article not found. (id=" + Integer.parseInt(args[2]) + ")");
                        }
                        System.out.println(articleManagement.getArticle(Integer.parseInt(args[2])));
                    } else {
                        System.out.println(articleManagement.getAllArticles());
                    }
                    break;
                case "delete":
                    if (args.length != 3) {
                        throw new IllegalArgumentException("Error: Invalid Parameter");
                    }
                    if (articleManagement.getArticle(Integer.parseInt(args[2])) == null) {
                        throw new IllegalArgumentException("Article not found. (id=" + Integer.parseInt(args[2]) + ")");
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
                            throw new IllegalArgumentException("Error: Invalid Parameter");
                        }
                    } else if (Objects.equals(args[1], "count")) {
                        System.out.println(articleManagement.getNumberOfArticles());
                    } else {
                        throw new IllegalArgumentException("Error: Invalid Parameter");
                    }
                    break;
                case "meanprice":
                    System.out.println(articleManagement.getMeanPrice());
                    break;
                case "oldest":
                    //TODO update method to output list of ildest IDs
                    System.out.println("ID: " + articleManagement.getOldestArticles());
                    break;
                default:
                    System.out.println("Error: Unknown command: " + command);
            }
        } catch (IOException e) {
            System.out.println("Unknown Error: " + e.getMessage());
        }
    }
}
