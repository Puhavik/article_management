import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ArticleManagement {
    private final ArticleDAO articleDAO;

    public ArticleManagement(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<Article> getAllArticles() throws FileNotFoundException {
        return articleDAO.getArticleList();
    }

    public Article getArticle(Integer id) throws FileNotFoundException {
        return articleDAO.getArticle(id);
    }

    public void saveArticle(Article article) {
        articleDAO.saveArticle(article);
    }

    public void deleteArticle(int id) throws FileNotFoundException {
        articleDAO.deleteArticle(id);
    }

    public int getNumberOfArticles() throws FileNotFoundException {
        return articleDAO.getArticleList().size();
    }

    public int getNumberOfBooks() throws FileNotFoundException {
        int count = 0;
        for (Article book : articleDAO.getArticleList()) {
            if (book instanceof Book) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfDVDs() throws FileNotFoundException {
        int count = 0;
        for (Article dvd : articleDAO.getArticleList()) {
            if (dvd instanceof DVD) {
                count++;
            }
        }
        return count;
    }

    public double getMeanPrice() throws FileNotFoundException {
        double sum = 0.0;
        for (Article article : articleDAO.getArticleList()) {
            sum += article.getPrice();
        }

        DecimalFormat df = new DecimalFormat("#.##");
        String roundedValue = df.format(sum / articleDAO.getArticleList().size());
        return Double.parseDouble(roundedValue);
    }

    public List<Integer> getOldestArticles() throws FileNotFoundException {
        int age = 0;

        List<Integer> ids = new ArrayList<>();
        for (Article article : articleDAO.getArticleList()) {
            if (article.getAge() > age) {
                age = article.getAge();
            }
        }
        for (Article article : articleDAO.getArticleList()) {
            if (article.getAge() == age) {
                ids.add(article.getId());
            }
        }
        return ids;
    }
}