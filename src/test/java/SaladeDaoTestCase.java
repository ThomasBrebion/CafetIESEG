import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Salades;
import dao.impl.DataSourceProvider;
import dao.impl.SaladeDaoImpl;
import dao.SaladesDao;

import org.junit.Assert;

public class SaladeDaoTestCase {
		
	private SaladesDao SaladesDao = new SaladeDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `salade`");
		stmt.executeUpdate("INSERT INTO `salade` (`nom`, `prix_solo`, `prix_menu`,`id`) VALUES	('Salade Composée', 3, 4.5,1)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerSalades() {
		List<Salades> Salades = SaladesDao.listerSalades();
		Assert.assertEquals(1, Salades.size());
		Assert.assertEquals("Salade Composée", Salades.get(0).getNom());
		Assert.assertEquals(3, Salades.get(0).getPrix_solo(),0);
		Assert.assertEquals(4.5, Salades.get(0).getPrix_menu(),0);
		Assert.assertEquals(1, Salades.get(0).getId());
	}
	
	@Test
	public void testGetSalades() {
		Salades Salades = SaladesDao.getSalade(1);
		Assert.assertNotNull(Salades);
		Assert.assertEquals("Salade Composée",Salades.getNom());
		Assert.assertEquals(3,Salades.getPrix_solo(),0);
		Assert.assertEquals(4.5,Salades.getPrix_menu(),0);
		Assert.assertEquals(1, Salades.getId());
	}
	
	@Test
	public void testGetSaladesSansResultat() {
		Salades Salades = SaladesDao.getSalade(0);
		Assert.assertNull(Salades);		
	}
	
	@Test
	public void testAjouterSalades() throws Exception {
		Salades Salades = new Salades("Salade chèvre",3.40,4.9,2);
		Salades SaladesAjoute = SaladesDao.ajouterSalade(Salades);
		
		Assert.assertEquals("Salade chèvre", SaladesAjoute.getNom());
		Assert.assertEquals(3.40, SaladesAjoute.getPrix_solo(),0);
		Assert.assertEquals(4.9, SaladesAjoute.getPrix_menu(),0);
		Assert.assertEquals(2, SaladesAjoute.getId());
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `salade` WHERE id = ?");
		stmt.setInt(1, SaladesAjoute.getId());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(SaladesAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(SaladesAjoute.getPrix_solo(),rs.getDouble("prix_solo"),0);
		Assert.assertEquals(SaladesAjoute.getPrix_menu(),rs.getDouble("prix_menu"),0);
		Assert.assertEquals(SaladesAjoute.getId(),rs.getInt("id"));
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerSalades() throws Exception {
		Salades Salades = new Salades("Salade mer",3.50,5,3);
		SaladesDao.ajouterSalade(Salades);
		
		int i = SaladesDao.listerSalades().size();
		Assert.assertEquals(2,i);
		
		SaladesDao.supprimerSalade(3);
		int j = SaladesDao.listerSalades().size();
		Assert.assertEquals(1,j);
		Salades Salades2 = SaladesDao.listerSalades().get(j-1);
		Assert.assertEquals("Salade Composée", Salades2.getNom());
		
	}
}