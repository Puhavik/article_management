import java.io.FileNotFoundException;
import java.util.List;

public interface ArticleDAO {

    public List<Article> getArticleList() throws FileNotFoundException;

    public Article getArticle(int id) throws FileNotFoundException;

    public void saveArticle(Article article);

    public void deleteArticle(int id) throws FileNotFoundException;
}
