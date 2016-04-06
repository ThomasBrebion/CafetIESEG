package dao;

import java.util.List;

import Entities.Article;

public interface ArticleDao {
	public List<Article> listerArticles();

	public Article getArticle(Integer id);

	public Article ajouterArticle(Article article);
	
	public void supprimerArticle(int id);
}
