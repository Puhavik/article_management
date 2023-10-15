import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class SerializedArticleDAO implements ArticleDAO {
    String fileName;

    public SerializedArticleDAO(String fileName) {
        this.fileName = fileName;
        File file = new File(fileName);
        if (!file.exists()){
            List<Article> articlesNew = new ArrayList<>();

            try {
                ObjectOutputStream writer = new ObjectOutputStream(new
                        FileOutputStream(fileName));
                writer.writeObject(articlesNew);
                writer.close();
            } catch (Exception e) {
                System.err.println("Error during serialization. " + e.getMessage());
                System.exit(1);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> getArticleList() {
        List<Article> articlesNew = new ArrayList<>();

//        System.out.println("In get Article List");
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            articlesNew = (List<Article>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
        return articlesNew;
    }

    @Override
    public Article getArticle(int id) {
        List<Article> articles = getArticleList();
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    @Override
    public void saveArticle(Article article) {
        if (getArticle(article.getId()) != null) {
            throw new IllegalArgumentException("Not allowed!");
        }
        List<Article> articlesNew = getArticleList();
        articlesNew.add(article);

        try {
            ObjectOutputStream writer = new ObjectOutputStream(new
                    FileOutputStream(fileName));
            writer.writeObject(articlesNew);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error during serialization. " + e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void deleteArticle(int id) throws FileNotFoundException {
        List<Article> articles = getArticleList();

        articles.removeIf(article -> article.getId() == id);
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new
                    FileOutputStream(fileName));
            writer.writeObject(articles);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error during serialization. " + e.getMessage());
            System.exit(1);
        }
    }
}
