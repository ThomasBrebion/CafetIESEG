package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Article;
import dao.ArticleDao;

/* Méthodes pour les articles */


public class ArticleDaoImpl implements ArticleDao {
	
	/* Cette méthode permet de lister tous les articles de la base de donnée pour pouvoir les affichers dans l'accueil */

	@Override
	public List<Article> listerArticles() {
		List<Article> listeDeArticle = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM article_text");
			while (resultSet.next()) {
				listeDeArticle.add(new Article(resultSet.getInt("id"), resultSet.getString("text"),resultSet.getString("auteur"),resultSet.getString("nom")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeArticle;
	}


	@Override
	public Article getArticle(Integer id) {
		
		/* Cette méthode permet de sélectionner un article grâce à son Id */
		
		Article article = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM article_text WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				article = new Article(resultSet.getInt("id"), resultSet.getString("text"),resultSet.getString("auteur"),resultSet.getString("nom"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	
	@Override
	public Article ajouterArticle(Article article) {
		
		/* Cette méthode permet d'ajouter un article sur la page d'accueil /*
		/* L'administrateur renseigne le nom de l'article, le texte, l'id ainsi que l'auteur */
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `article_text`(`nom`,`text`,`id`,`auteur`)VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getTitre());
			stmt.setString(2, article.getText());
			stmt.setInt(3, article.getId());
			stmt.setString(4, article.getAuteur());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	
	@Override
	public void supprimerArticle(int id) {
		
		/* Cette méthode permet de supprimer un article sur la page d'accueil */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `article_text` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}		
		}
	

	@Override
	public void majArticle(Article article) {
		
		/* Cette méthode permet de modifier un article sur la page d'accueil */
		
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `article_text` SET `nom`=?,`text`=?,`auteur`=? WHERE `id`=?");
			stmt.setString(1, article.getTitre());
			stmt.setString(2, article.getText());
			stmt.setString(3, article.getAuteur());
			stmt.setInt(4, article.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}