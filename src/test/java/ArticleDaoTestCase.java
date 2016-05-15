import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import Entities.Article;
import dao.ArticleDao;
import dao.impl.ArticleDaoImpl;
import dao.impl.DataSourceProvider;

import org.junit.Assert;

public class ArticleDaoTestCase {
		
	private ArticleDao articleDao = new ArticleDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `article_text`");
		stmt.executeUpdate("INSERT INTO `article_text`(`nom`,`text`,`id`,`auteur`) VALUES ('Hello la compagnie !','Bonjour Ieseg, comment ca va ? Nous : top !',2,'Thomas')");
		stmt.executeUpdate("INSERT INTO `article_text`(`nom`,`text`,`id`,`auteur`) VALUES ('Le Croc'' Baguette', 'L''indétronable Croc'' Baguette est de retour à la Cafet'' demain pour une série limitée !\r\n\r\nAlors si tu veux commencer la semaine sainement (ou pas), que tu sois friant de baguette, de fromage ou de jambon, n''hésite pas à venir nous voir entre 12h et 14h !\r\n\r\nBisous gourmands et friandises !', 1, 'Cafet''IESEG')");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerArticles() {
		List<Article> articles = articleDao.listerArticles();
		Assert.assertEquals(2, articles.size());
		Assert.assertEquals(2, articles.get(0).getId().intValue());
		Assert.assertEquals("Hello la compagnie !", articles.get(0).getTitre());
		Assert.assertEquals("Bonjour Ieseg, comment ca va ? Nous : top !", articles.get(0).getText());
		Assert.assertEquals("Thomas", articles.get(0).getAuteur());
		Assert.assertEquals(1, articles.get(1).getId().intValue());
		Assert.assertEquals("Le Croc' Baguette", articles.get(1).getTitre());
		Assert.assertEquals("L'indétronable Croc' Baguette est de retour à la Cafet' demain pour une série limitée !\r\n\r\nAlors si tu veux commencer la semaine sainement (ou pas), que tu sois friant de baguette, de fromage ou de jambon, n'hésite pas à venir nous voir entre 12h et 14h !\r\n\r\nBisous gourmands et friandises !", articles.get(1).getText());
		Assert.assertEquals("Cafet'IESEG", articles.get(1).getAuteur());
	}
	
	@Test
	public void testGetArticle() {
		Article article = articleDao.getArticle(1);
		Assert.assertNotNull(article);
		Assert.assertEquals(1,article.getId().intValue());
		Assert.assertEquals("Le Croc' Baguette", article.getTitre());
		Assert.assertEquals("L'indétronable Croc' Baguette est de retour à la Cafet' demain pour une série limitée !\r\n\r\nAlors si tu veux commencer la semaine sainement (ou pas), que tu sois friant de baguette, de fromage ou de jambon, n'hésite pas à venir nous voir entre 12h et 14h !\r\n\r\nBisous gourmands et friandises !", article.getText());
		Assert.assertEquals("Cafet'IESEG", article.getAuteur());
	}
	
	@Test
	public void testGetArticleSansResultat() {
		Article article = articleDao.getArticle(-1);
		Assert.assertNull(article);		
	}
	
	@Test
	public void testAjouterArticle() throws Exception {
		Article article = new Article(3,"Hello IESEG, j espere que ça baigne !","Tom","Salut IESEG");
		Article articleAjoute = articleDao.ajouterArticle(article);
		
		Assert.assertEquals(3,articleAjoute.getId().intValue());
		Assert.assertEquals("Salut IESEG", articleAjoute.getTitre());
		Assert.assertEquals("Hello IESEG, j espere que ça baigne !", articleAjoute.getText());
		Assert.assertEquals("Tom", articleAjoute.getAuteur());
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `article_text` WHERE id = 3");
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(3,rs.getInt("id"));
		Assert.assertEquals("Salut IESEG",rs.getString("nom"));
		Assert.assertEquals("Hello IESEG, j espere que ça baigne !",rs.getString("text"));
		Assert.assertEquals("Tom",rs.getString("auteur"));
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerArticle() throws Exception {
		Article article = new Article(4,"Hello IESEG, j espere que ça baigne !","Tom","Salut IESEG");
		articleDao.ajouterArticle(article);
		
		int i = articleDao.listerArticles().size();
		Assert.assertEquals(3,i);
		
		articleDao.supprimerArticle(4);
		int j = articleDao.listerArticles().size();
		Assert.assertEquals(2,j);
		Article article1 = articleDao.listerArticles().get(j-1);
		Assert.assertEquals("Le Croc' Baguette", article1.getTitre());
		Article article2 = articleDao.listerArticles().get(j-2);
		Assert.assertEquals("Hello la compagnie !", article2.getTitre());
		
	}
}