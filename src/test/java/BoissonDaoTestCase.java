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
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`,`id`) VALUES ('Soda(33cL)',0.90,1)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`,`id`) VALUES ('Red Bull',2.00,2)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`,`id`) VALUES ('Eau plate / gazeuse (50cL)',0.50,3)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`,`id`) VALUES ('Café',0.30,4)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`,`id`) VALUES ('Thé',0.30,5)");
		stmt.executeUpdate("INSERT INTO `boissons`(`nom`,`prix`,`id`) VALUES ('Jus de fruit',0.30,6)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerBoissons() {
		List<Boissons> boissons = boissonDao.listerBoissons();
		Assert.assertEquals(6, boissons.size());
		Assert.assertEquals("Café", boissons.get(0).getNom());
		Assert.assertEquals(0.30, boissons.get(0).getPrix(),0);
		Assert.assertEquals(4, boissons.get(0).getId());
		Assert.assertEquals("Thé", boissons.get(1).getNom());
		Assert.assertEquals(0.30, boissons.get(1).getPrix(),0);
		Assert.assertEquals(5, boissons.get(1).getId());
	}
	
	@Test
	public void testGetBoisson() {
		Boissons boisson = boissonDao.getBoissons(4);
		Assert.assertNotNull(boisson);
		Assert.assertEquals("Café",boisson.getNom());
		Assert.assertEquals(0.30,boisson.getPrix(),0);
	}
	
	@Test
	public void testGetBoissonSansResultat() {
		Boissons boisson = boissonDao.getBoissons(0);
		Assert.assertNull(boisson);		
	}
	
	@Test
	public void testAjouterBoisson() throws Exception {
		Boissons boisson = new Boissons("Sprite",0.60,7);
		Boissons boissonAjoute = boissonDao.ajouterBoissons(boisson);
		
		Assert.assertEquals("Sprite", boissonAjoute.getNom());
		Assert.assertEquals(0.60, boissonAjoute.getPrix(),0);
		Assert.assertEquals(7, boissonAjoute.getId());
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `boissons` WHERE id = ?");
		stmt.setInt(1, boissonAjoute.getId());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(boissonAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(boissonAjoute.getPrix(),rs.getDouble("prix"),0);
		Assert.assertFalse(rs.next());
		boissonDao.supprimerBoissons(7);
		connection.close();
	}
	
	@Test
	public void testSupprimerBoisson() throws Exception {
		Boissons boisson = new Boissons("Jus d'orange",0.30,8);
		boissonDao.ajouterBoissons(boisson);
		
		int i = boissonDao.listerBoissons().size();
		Assert.assertEquals(7,i);

		boissonDao.supprimerBoissons(8);
		int j = boissonDao.listerBoissons().size();
		Assert.assertEquals(6,j);
		Boissons boisson1 = boissonDao.listerBoissons().get(j-1);
		Assert.assertEquals("Red Bull", boisson1.getNom());
		Boissons boisson2 = boissonDao.listerBoissons().get(j-2);
		Assert.assertEquals("Soda(33cL)", boisson2.getNom());
		Boissons boisson3 = boissonDao.listerBoissons().get(j-3);
		Assert.assertEquals("Eau plate / gazeuse (50cL)", boisson3.getNom());
		Boissons boisson4 = boissonDao.listerBoissons().get(j-4);
		Assert.assertEquals("Jus de fruit", boisson4.getNom());
		Boissons boisson5 = boissonDao.listerBoissons().get(j-5);
		Assert.assertEquals("Thé", boisson5.getNom());
		Boissons boisson6 = boissonDao.listerBoissons().get(j-6);
		Assert.assertEquals("Café", boisson6.getNom());
		
	}
}