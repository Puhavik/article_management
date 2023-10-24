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
            throw new IllegalArgumentException("Error: Article already exists. (id=" + article.getId() + ")");
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
    public void deleteArticle(int id) {
        try {
            List<Article> articles = getArticleList();
            boolean found = false;

            for (Article article : articles) {
                if (article.getId() == id) {
                    articles.remove(article);
                    found = true;
                    break;
                } else if (article.getId() == null){
                    throw new IllegalArgumentException("Illegal Parameter.");
                }
            }

            if (!found) {
                System.err.println("Article not found.");
                return;
            }

            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName));
            writer.writeObject(articles);
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during serialization: " + e.getMessage());
            System.exit(1);
        }
    }
}

