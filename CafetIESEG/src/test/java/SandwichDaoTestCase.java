import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Sandwich;
import dao.impl.DataSourceProvider;
import dao.impl.SandwichDaoImpl;
import dao.SandwichDao;

import org.junit.Assert;

public class SandwichDaoTestCase {
		
	private SandwichDao SandwichDao = new SandwichDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `sandwich`");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('3 Fromages', 3, 4.5)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Crabe', 3, 4.5)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Emmental', 2.5, 4)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Jambon', 2.6, 4.2)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Mimolette', 2.5, 4)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Poulet Curry', 3.2, 4.7)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Poulet Mayo', 3.2, 4.7)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Sandwich de la Semaine', 3.2, 4.7)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Thon', 3, 4.5)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Total Beurre', 3, 4.5)");
		stmt.executeUpdate("INSERT INTO `sandwich` (`nom`, `prix_solo`, `prix_menu`) VALUES ('Total Mayo', 3, 4.5)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerSandwich() {
		List<Sandwich> Sandwich = SandwichDao.listerSandwichs();
		Assert.assertEquals(11, Sandwich.size());
		Assert.assertEquals("Emmental", Sandwich.get(0).getNom());
		Assert.assertEquals(2.5, Sandwich.get(0).getPrix_solo(),0);
		Assert.assertEquals(4, Sandwich.get(0).getPrix_menu(),0);
		Assert.assertEquals("Mimolette", Sandwich.get(1).getNom());
		Assert.assertEquals(2.5, Sandwich.get(1).getPrix_solo(),0);
		Assert.assertEquals(4, Sandwich.get(1).getPrix_menu(),0);
	}
	
	@Test
	public void testGetSandwich() {
		Sandwich Sandwich = SandwichDao.getSandwich("Thon");
		Assert.assertNotNull(Sandwich);
		Assert.assertEquals("Thon",Sandwich.getNom());
		Assert.assertEquals(3,Sandwich.getPrix_solo(),0);
		Assert.assertEquals(4.5,Sandwich.getPrix_menu(),0);
	}
	
	@Test
	public void testGetSandwichSansResultat() {
		Sandwich Sandwich = SandwichDao.getSandwich("");
		Assert.assertNull(Sandwich);		
	}
	
	@Test
	public void testAjouterSandwich() throws Exception {
		Sandwich Sandwich = new Sandwich("Carbo",1.20,3.5);
		Sandwich SandwichAjoute = SandwichDao.ajouterSandwich(Sandwich);
		
		Assert.assertEquals("Carbo", SandwichAjoute.getNom());
		Assert.assertEquals(1.20, SandwichAjoute.getPrix_solo(),0);
		Assert.assertEquals(3.5, SandwichAjoute.getPrix_menu(),0);
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `sandwich` WHERE nom = ?");
		stmt.setString(1, SandwichAjoute.getNom());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(SandwichAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(SandwichAjoute.getPrix_solo(),rs.getDouble("prix_solo"),0);
		Assert.assertEquals(SandwichAjoute.getPrix_menu(),rs.getDouble("prix_menu"),0);
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerSandwich() throws Exception {
		Sandwich Sandwich = new Sandwich("Pomme de terre",2.30,4.3);
		SandwichDao.ajouterSandwich(Sandwich);
		
		int i = SandwichDao.listerSandwichs().size();
		Assert.assertEquals(12,i);
		
		SandwichDao.supprimerSandwich("Pomme de terre");
		int j = SandwichDao.listerSandwichs().size();
		Assert.assertEquals(11,j);
		Sandwich Sandwich2 = SandwichDao.listerSandwichs().get(j-1);
		Assert.assertEquals("Poulet Curry", Sandwich2.getNom());
		Sandwich Sandwich3 = SandwichDao.listerSandwichs().get(j-2);
		Assert.assertEquals("Sandwich de la Semaine", Sandwich3.getNom());
		Sandwich Sandwich4 = SandwichDao.listerSandwichs().get(j-3);
		Assert.assertEquals("Poulet Mayo", Sandwich4.getNom());
		Sandwich Sandwich5 = SandwichDao.listerSandwichs().get(j-4);
		Assert.assertEquals("Crabe", Sandwich5.getNom());
		Sandwich Sandwich6 = SandwichDao.listerSandwichs().get(j-5);
		Assert.assertEquals("Total Mayo", Sandwich6.getNom());
		Sandwich Sandwich7 = SandwichDao.listerSandwichs().get(j-6);
		Assert.assertEquals("Thon", Sandwich7.getNom());
		Sandwich Sandwich8 = SandwichDao.listerSandwichs().get(j-7);
		Assert.assertEquals("Total Beurre", Sandwich8.getNom());
		Sandwich Sandwich9 = SandwichDao.listerSandwichs().get(j-8);
		Assert.assertEquals("3 Fromages", Sandwich9.getNom());
		Sandwich Sandwich10 = SandwichDao.listerSandwichs().get(j-9);
		Assert.assertEquals("Jambon", Sandwich10.getNom());
		Sandwich Sandwich11 = SandwichDao.listerSandwichs().get(j-10);
		Assert.assertEquals("Mimolette", Sandwich11.getNom());
		Sandwich Sandwich12 = SandwichDao.listerSandwichs().get(j-11);
		Assert.assertEquals("Emmental", Sandwich12.getNom());
		
	}
}