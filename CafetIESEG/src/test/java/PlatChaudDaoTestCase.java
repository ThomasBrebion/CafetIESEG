import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Plat_chaud;
import dao.impl.DataSourceProvider;
import dao.impl.Plat_chaudDaoImpl;
import dao.Plat_chaudDao;

import org.junit.Assert;

public class PlatChaudDaoTestCase {
		
	private Plat_chaudDao Plat_chaudDao = new Plat_chaudDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `plat_chaud`");
		stmt.executeUpdate("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`) VALUES ('Croque Baguette Double', 3.5, 4.9)");
		stmt.executeUpdate("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`) VALUES ('Croque Baguette Simple', 2.6, 4.2)");
		stmt.executeUpdate("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`) VALUES ('Hot-Dog', 2.6, 4.2)");
		stmt.executeUpdate("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`) VALUES ('Pasta Box (Bolognaise, Carbonara, 3 Fromages)', 3.5, 4.9)");
		stmt.executeUpdate("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`) VALUES ('Pizza', 2.6, 4.2)");
		stmt.executeUpdate("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`) VALUES ('Potato Burger', 3.2, 4.7)");
		stmt.executeUpdate("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`) VALUES ('Quiche', 2.6, 4.2)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerPlat_chaud() {
		List<Plat_chaud> Plat_chaud = Plat_chaudDao.listerPlat_chaud();
		Assert.assertEquals(7, Plat_chaud.size());
		Assert.assertEquals("Croque Baguette Simple", Plat_chaud.get(0).getNom());
		Assert.assertEquals(2.6, Plat_chaud.get(0).getPrix_solo(),0);
		Assert.assertEquals(4.2, Plat_chaud.get(0).getPrix_menu(),0);
		Assert.assertEquals("Hot-Dog", Plat_chaud.get(1).getNom());
		Assert.assertEquals(2.6, Plat_chaud.get(1).getPrix_solo(),0);
		Assert.assertEquals(4.2, Plat_chaud.get(1).getPrix_menu(),0);
	}
	
	@Test
	public void testGetPlat_chaud() {
		Plat_chaud Plat_chaud = Plat_chaudDao.getPlat_chaud("Pizza");
		Assert.assertNotNull(Plat_chaud);
		Assert.assertEquals("Pizza",Plat_chaud.getNom());
		Assert.assertEquals(2.6,Plat_chaud.getPrix_solo(),0);
		Assert.assertEquals(4.2,Plat_chaud.getPrix_menu(),0);
	}
	
	@Test
	public void testGetPlat_chaudSansResultat() {
		Plat_chaud Plat_chaud = Plat_chaudDao.getPlat_chaud("");
		Assert.assertNull(Plat_chaud);		
	}
	
	@Test
	public void testAjouterPlat_chaud() throws Exception {
		Plat_chaud Plat_chaud = new Plat_chaud("Carbo",1.20,3.5);
		Plat_chaud Plat_chaudAjoute = Plat_chaudDao.ajouterPlat_chaud(Plat_chaud);
		
		Assert.assertEquals("Carbo", Plat_chaudAjoute.getNom());
		Assert.assertEquals(1.20, Plat_chaudAjoute.getPrix_solo(),0);
		Assert.assertEquals(3.5, Plat_chaudAjoute.getPrix_menu(),0);
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `plat_chaud` WHERE nom = ?");
		stmt.setString(1, Plat_chaudAjoute.getNom());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(Plat_chaudAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(Plat_chaudAjoute.getPrix_solo(),rs.getDouble("prix_solo"),0);
		Assert.assertEquals(Plat_chaudAjoute.getPrix_menu(),rs.getDouble("prix_menu"),0);
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerPlat_chaud() throws Exception {
		Plat_chaud Plat_chaud = new Plat_chaud("Pomme de terre",2.30,4.3);
		Plat_chaudDao.ajouterPlat_chaud(Plat_chaud);
		
		int i = Plat_chaudDao.listerPlat_chaud().size();
		Assert.assertEquals(8,i);
		
		Plat_chaudDao.supprimerPlat_chaud("Pomme de terre");
		int j = Plat_chaudDao.listerPlat_chaud().size();
		Assert.assertEquals(7,j);
		Plat_chaud Plat_chaud2 = Plat_chaudDao.listerPlat_chaud().get(j-1);
		Assert.assertEquals("Pasta Box (Bolognaise, Carbonara, 3 Fromages)", Plat_chaud2.getNom());
		Plat_chaud Plat_chaud3 = Plat_chaudDao.listerPlat_chaud().get(j-2);
		Assert.assertEquals("Croque Baguette Double", Plat_chaud3.getNom());
		Plat_chaud Plat_chaud4 = Plat_chaudDao.listerPlat_chaud().get(j-3);
		Assert.assertEquals("Potato Burger", Plat_chaud4.getNom());
		Plat_chaud Plat_chaud5 = Plat_chaudDao.listerPlat_chaud().get(j-4);
		Assert.assertEquals("Quiche", Plat_chaud5.getNom());
		Plat_chaud Plat_chaud6 = Plat_chaudDao.listerPlat_chaud().get(j-5);
		Assert.assertEquals("Pizza", Plat_chaud6.getNom());
		Plat_chaud Plat_chaud7 = Plat_chaudDao.listerPlat_chaud().get(j-6);
		Assert.assertEquals("Hot-Dog", Plat_chaud7.getNom());
		Plat_chaud Plat_chaud8 = Plat_chaudDao.listerPlat_chaud().get(j-7);
		Assert.assertEquals("Croque Baguette Simple", Plat_chaud8.getNom());
		
	}
}