import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Plat;
import dao.impl.DataSourceProvider;
import dao.impl.PlatDaoImpl;
import dao.PlatDao;

import org.junit.Assert;

public class PlatDaoTestCase {
		
	private PlatDao PlatDao = new PlatDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `Plat`");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Croque Baguette Double', 4.9)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Croque Baguette Simple', 4.2)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Hot-Dog', 4.2)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Pasta Box (Bolognaise, Carbonara, 3 Fromages)', 4.9)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Pizza', 4.2)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Potato Burger', 4.7)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Quiche', 4.2)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Salade Composée', 4.5)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('3 Fromages', 4.5)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Crabe', 4.5)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Emmental', 4)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Jambon', 4.2)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Mimolette', 4)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Poulet Curry', 4.7)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Poulet Mayo', 4.7)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Sandwich de la Semaine', 4.7)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Thon', 4.5)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Total Beurre', 4.5)");
		stmt.executeUpdate("INSERT INTO `Plat`(`nom`,`prix`) VALUES ('Total Mayo', 4.5)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerPlat() {
		List<Plat> Plat = PlatDao.listerPlat();
		Assert.assertEquals(19, Plat.size());
		Assert.assertEquals("Croque Baguette Simple", Plat.get(0).getNom());
		Assert.assertEquals(4.2, Plat.get(0).getPrix(),0);
		Assert.assertEquals("Hot-Dog", Plat.get(1).getNom());
		Assert.assertEquals(4.2, Plat.get(0).getPrix(),0);
	}
	
	@Test
	public void testGetPlat() {
		Plat Plat = PlatDao.getPlat("Pizza");
		Assert.assertNotNull(Plat);
		Assert.assertEquals("Pizza",Plat.getNom());
		Assert.assertEquals(4.2,Plat.getPrix(),0);
	}
	
	@Test
	public void testGetPlatSansResultat() {
		Plat Plat = PlatDao.getPlat("");
		Assert.assertNull(Plat);		
	}
}