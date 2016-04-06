import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Boissons;
import dao.BoissonsDao;
import dao.impl.BoissonDaoImpl;
import dao.impl.DataSourceProvider;

import org.junit.Assert;

public class BoissonDaoTestCase {
		
	private BoissonsDao boissonDao = new BoissonDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `boissons`");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`) VALUES ('Soda(33cL)',0.90)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`) VALUES ('Red Bull',2.00)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`) VALUES ('Eau plate / gazeuse (50cL)',0.50)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`) VALUES ('Café',0.30)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`) VALUES ('Thé',0.30)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`) VALUES ('Jus de fruit',0.30)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerBoissons() {
		List<Boissons> boissons = boissonDao.listerBoissons();  /* Il existe un ORDER BY prix dans la méthode listerBoissons() */
		Assert.assertEquals(6, boissons.size());
		Assert.assertEquals("Café", boissons.get(0).getNom());
		Assert.assertEquals(0.30, boissons.get(0).getPrix(),0);
		Assert.assertEquals("Jus de fruit", boissons.get(1).getNom());
		Assert.assertEquals(0.30, boissons.get(1).getPrix(),0);
	}
	
	@Test
	public void testGetBoisson() {
		Boissons boisson = boissonDao.getBoissons("Café");
		Assert.assertNotNull(boisson);
		Assert.assertEquals("Café",boisson.getNom());
		Assert.assertEquals(0.30,boisson.getPrix(),0);
	}
	
	@Test
	public void testGetBoissonSansResultat() {
		Boissons boisson = boissonDao.getBoissons("");
		Assert.assertNull(boisson);		
	}
	
	@Test
	public void testAjouterBoisson() throws Exception {
		Boissons boisson = new Boissons("Sprite",0.60);
		Boissons boissonAjoute = boissonDao.ajouterBoissons(boisson);
		
		Assert.assertEquals("Sprite", boissonAjoute.getNom());
		Assert.assertEquals(0.60, boissonAjoute.getPrix(),0);
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `boissons` WHERE nom = ?");
		stmt.setString(1, boissonAjoute.getNom());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(boissonAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(boissonAjoute.getPrix(),rs.getDouble("prix"),0);
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerBoisson() throws Exception {
		Boissons boisson = new Boissons("Jus d'orange",0.30);
		boissonDao.ajouterBoissons(boisson);
		
		int i = boissonDao.listerBoissons().size();
		Assert.assertEquals(7,i);
		
		boissonDao.supprimerBoissons("Jus d'orange");
		int j = boissonDao.listerBoissons().size();
		Assert.assertEquals(6,j);
		Boissons boisson1 = boissonDao.listerBoissons().get(j-1);
		Assert.assertEquals("Red Bull", boisson1.getNom());
		Boissons boisson2 = boissonDao.listerBoissons().get(j-2);
		Assert.assertEquals("Soda(33cL)", boisson2.getNom());
		Boissons boisson3 = boissonDao.listerBoissons().get(j-3);
		Assert.assertEquals("Eau plate / gazeuse (50cL)", boisson3.getNom());
		Boissons boisson4 = boissonDao.listerBoissons().get(j-4);
		Assert.assertEquals("Thé", boisson4.getNom());
		Boissons boisson5 = boissonDao.listerBoissons().get(j-5);
		Assert.assertEquals("Jus de fruit", boisson5.getNom());
		Boissons boisson6 = boissonDao.listerBoissons().get(j-6);
		Assert.assertEquals("Café", boisson6.getNom());
		
	}
}