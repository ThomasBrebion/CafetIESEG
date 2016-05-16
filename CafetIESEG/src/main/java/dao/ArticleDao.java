package dao;

import java.util.List;
import Entities.Article;

/* Interface avec toutes les méthodes utilisée pour les articles */

public interface ArticleDao {
	public List<Article> listerArticles();

	public Article getArticle(Integer id);

	public Article ajouterArticle(Article article);
	
	public void supprimerArticle(int id);
	
	public void majArticle(Article article);
}
