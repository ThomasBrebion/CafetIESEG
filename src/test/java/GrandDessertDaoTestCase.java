import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Grand_dessert;
import dao.Grand_dessertDao;
import dao.impl.DataSourceProvider;
import dao.impl.Grand_dessertDaoImpl;

import org.junit.Assert;

public class GrandDessertDaoTestCase {
		
	private Grand_dessertDao Grand_DessertDao = new Grand_dessertDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `Grand_Dessert`");
		stmt.executeUpdate("INSERT INTO `Grand_Dessert`(`nom`,`prix`,`id`) VALUES ('Barre chocolatée', 0.8,1)");
		stmt.executeUpdate("INSERT INTO `Grand_Dessert`(`nom`,`prix`,`id`) VALUES ('Chips', 0.6,2)");
		stmt.executeUpdate("INSERT INTO `Grand_Dessert`(`nom`,`prix`,`id`) VALUES ('Kinder Country', 0.6,3)");
		stmt.executeUpdate("INSERT INTO `Grand_Dessert`(`nom`,`prix`,`id`) VALUES ('Viennoiserie', 0.8,4)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerGrand_Dessert() {
		List<Grand_dessert> Grand_Dessert = Grand_DessertDao.listerGrand_dessert();
		Assert.assertEquals(4, Grand_Dessert.size());
		Assert.assertEquals("Chips", Grand_Dessert.get(0).getNom());
		Assert.assertEquals(0.6, Grand_Dessert.get(0).getPrix(),0);
		Assert.assertEquals(2, Grand_Dessert.get(0).getId());
		Assert.assertEquals("Kinder Country", Grand_Dessert.get(1).getNom());
		Assert.assertEquals(0.6, Grand_Dessert.get(1).getPrix(),0);
		Assert.assertEquals(3, Grand_Dessert.get(1).getId(),0);
	}
	
	@Test
	public void testGetGrand_Dessert() {
		Grand_dessert Grand_Dessert = Grand_DessertDao.getGrand_dessert(2);
		Assert.assertNotNull(Grand_Dessert);
		Assert.assertEquals("Chips",Grand_Dessert.getNom());
		Assert.assertEquals(0.6,Grand_Dessert.getPrix(),0);
	}
	
	@Test
	public void testGetGrandDessertSansResultat() {
		Grand_dessert Grand_Dessert = Grand_DessertDao.getGrand_dessert(0);
		Assert.assertNull(Grand_Dessert);		
	}
	
	@Test
	public void testAjouterGrandDessert() throws Exception {
		Grand_dessert Grand_Dessert = new Grand_dessert("Chocolat",1.20,5);
		Grand_dessert Grand_DessertAjoute = Grand_DessertDao.ajouterGrand_dessert(Grand_Dessert);
		
		Assert.assertEquals("Chocolat", Grand_DessertAjoute.getNom());
		Assert.assertEquals(1.20, Grand_DessertAjoute.getPrix(),0);
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `Grand_Dessert` WHERE id = ?");
		stmt.setInt(1, Grand_DessertAjoute.getId());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(Grand_DessertAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(Grand_DessertAjoute.getPrix(),rs.getDouble("prix"),0);
		Assert.assertEquals(Grand_DessertAjoute.getId(),rs.getInt("id"));
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerGrandDessert() throws Exception {
		Grand_dessert Grand_Dessert = new Grand_dessert("Bonbon",0.30,6);
		Grand_DessertDao.ajouterGrand_dessert(Grand_Dessert);
		
		int i = Grand_DessertDao.listerGrand_dessert().size();
		Assert.assertEquals(5,i);
		
		Grand_DessertDao.supprimerGrand_dessert(6);
		int j = Grand_DessertDao.listerGrand_dessert().size();
		Assert.assertEquals(4,j);
		Grand_dessert Grand_Dessert2 = Grand_DessertDao.listerGrand_dessert().get(j-1);
		Assert.assertEquals("Viennoiserie", Grand_Dessert2.getNom());
		Grand_dessert Grand_Dessert3 = Grand_DessertDao.listerGrand_dessert().get(j-2);
		Assert.assertEquals("Barre chocolatée", Grand_Dessert3.getNom());
		Grand_dessert Grand_Dessert4 = Grand_DessertDao.listerGrand_dessert().get(j-3);
		Assert.assertEquals("Kinder Country", Grand_Dessert4.getNom());
		Grand_dessert Grand_Dessert5 = Grand_DessertDao.listerGrand_dessert().get(j-4);
		Assert.assertEquals("Chips", Grand_Dessert5.getNom());
		
	}
}