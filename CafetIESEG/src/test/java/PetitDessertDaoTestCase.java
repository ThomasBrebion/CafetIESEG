import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Petit_dessert;
import dao.Petit_dessertDao;
import dao.impl.DataSourceProvider;
import dao.impl.Petit_dessertDaoImpl;

import org.junit.Assert;

public class PetitDessertDaoTestCase {
		
	private Petit_dessertDao Petit_dessertDao = new Petit_dessertDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `Petit_dessert`");
		stmt.executeUpdate("INSERT INTO `Petit_dessert`(`nom`,`prix`,`id`) VALUES ('Bonbons Haribo', 0.5,1)");
		stmt.executeUpdate("INSERT INTO `Petit_dessert`(`nom`,`prix`,`id`) VALUES ('Compote', 0.5,2)");
		stmt.executeUpdate("INSERT INTO `Petit_dessert`(`nom`,`prix`,`id`) VALUES ('Fruit', 0.5,3)");
		stmt.executeUpdate("INSERT INTO `Petit_dessert`(`nom`,`prix`,`id`) VALUES ('Sucette', 0.3,4)");
		stmt.executeUpdate("INSERT INTO `Petit_dessert`(`nom`,`prix`,`id`) VALUES ('Yaourt', 0.5,5)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerPetit_dessert() {
		List<Petit_dessert> Petit_dessert = Petit_dessertDao.listerPetit_dessert();
		Assert.assertEquals(5, Petit_dessert.size());
		Assert.assertEquals("Sucette", Petit_dessert.get(0).getNom());
		Assert.assertEquals(0.3, Petit_dessert.get(0).getPrix(),0);
		Assert.assertEquals(4, Petit_dessert.get(0).getId());
		Assert.assertEquals("Bonbons Haribo", Petit_dessert.get(1).getNom());
		Assert.assertEquals(0.5, Petit_dessert.get(1).getPrix(),0);
		Assert.assertEquals(1, Petit_dessert.get(1).getId());
	}
	
	@Test
	public void testGetPetit_dessert() {
		Petit_dessert Petit_dessert = Petit_dessertDao.getPetit_dessert(2);
		Assert.assertNotNull(Petit_dessert);
		Assert.assertEquals("Compote",Petit_dessert.getNom());
		Assert.assertEquals(0.5,Petit_dessert.getPrix(),0);
	}
	
	@Test
	public void testGetGrandDessertSansResultat() {
		Petit_dessert Petit_dessert = Petit_dessertDao.getPetit_dessert(0);
		Assert.assertNull(Petit_dessert);		
	}
	
	@Test
	public void testAjouterGrandDessert() throws Exception {
		Petit_dessert Petit_dessert = new Petit_dessert("Chocolat",1.20,70);
		Petit_dessert Petit_dessertAjoute = Petit_dessertDao.ajouterPetit_dessert(Petit_dessert);
		
		Assert.assertEquals("Chocolat", Petit_dessertAjoute.getNom());
		Assert.assertEquals(1.20, Petit_dessertAjoute.getPrix(),0);
		Assert.assertEquals(70, Petit_dessertAjoute.getId());
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `Petit_dessert` WHERE nom = ?");
		stmt.setString(1, Petit_dessertAjoute.getNom());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(Petit_dessertAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(Petit_dessertAjoute.getPrix(),rs.getDouble("prix"),0);
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerArticle() throws Exception {
		Petit_dessert Petit_dessert = new Petit_dessert("Bonbon",0.30,9);
		Petit_dessertDao.ajouterPetit_dessert(Petit_dessert);
		
		int i = Petit_dessertDao.listerPetit_dessert().size();
		Assert.assertEquals(6,i);
		
		Petit_dessertDao.supprimerPetit_dessert(9);
		int j = Petit_dessertDao.listerPetit_dessert().size();
		Assert.assertEquals(5,j);
		Petit_dessert Petit_dessert2 = Petit_dessertDao.listerPetit_dessert().get(j-1);
		Assert.assertEquals("Yaourt", Petit_dessert2.getNom());
		Petit_dessert Petit_dessert3 = Petit_dessertDao.listerPetit_dessert().get(j-2);
		Assert.assertEquals("Fruit", Petit_dessert3.getNom());
		Petit_dessert Petit_dessert4 = Petit_dessertDao.listerPetit_dessert().get(j-3);
		Assert.assertEquals("Compote", Petit_dessert4.getNom());
		Petit_dessert Petit_dessert5 = Petit_dessertDao.listerPetit_dessert().get(j-4);
		Assert.assertEquals("Bonbons Haribo", Petit_dessert5.getNom());
		Petit_dessert Petit_dessert6 = Petit_dessertDao.listerPetit_dessert().get(j-5);
		Assert.assertEquals("Sucette", Petit_dessert6.getNom());
		
	}
}