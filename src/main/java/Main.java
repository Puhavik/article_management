

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DVD dvd1 = new DVD(5, "Armageddon", 2023, "Aria", 100, 60, 0);
        Book book1 = new Book(4, "Gore Ot Uma", 2014, "Griboedov", 17.99, 767);
//        System.out.println(dvd1);
//        System.out.println(book1);

//--------------------------------------------------------------
//        Check serialisation Class
// --------------------------------------------------------------

//        SerializedArticleDAO serializedArticleDAO = new SerializedArticleDAO("articles.txt");
//        serializedArticleDAO.saveArticle(dvd1);
//        serializedArticleDAO.saveArticle(book1);

        //System.out.println(serializedArticleDAO.getArticleList());
//        serializedArticleDAO.deleteArticle(5);
//        System.out.println(serializedArticleDAO.getArticleList());


// --------------------------------------------------------------
//        Check ArticleManagement Class
// --------------------------------------------------------------


        ArticleDAO articleDAO = new SerializedArticleDAO("articles.txt");

//        articleDAO.saveArticle(dvd1);
//        articleDAO.saveArticle(book1);
        ArticleManagement articleManagement = new ArticleManagement(articleDAO);

        System.out.println(articleManagement.getAllArticles());
//        System.out.println(articleManagement.getArticle(3));
//        articleManagement.saveArticle(book1);
//        System.out.println(articleManagement.getAllArticles());
//        articleManagement.deleteArticle(4);
//        System.out.println(articleManagement.getAllArticles());
//        System.out.println(articleManagement.getNumberOfArticles());
//        System.out.println(articleManagement.getNumberOfBooks());
//        System.out.println(articleManagement.getNumberOfDVDs());
//        System.out.println(articleManagement.getMeanPrice());
//        System.out.println((articleManagement.getOldestArticle()));
    }

}