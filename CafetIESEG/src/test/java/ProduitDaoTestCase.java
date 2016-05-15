import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Produits;
import dao.impl.DataSourceProvider;
import dao.impl.ProduitsDaoImpl;
import dao.ProduitsDao;

import org.junit.Assert;

public class ProduitDaoTestCase {
		
	private ProduitsDao ProduitsDao = new ProduitsDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `Produits`");
		stmt.executeUpdate("INSERT INTO `Produits`(`nom`, `id`, `date_peremption`, `prix`, `quantite`) VALUES ('viande', 1, '2016-07-22', 15.6, 3)");
		stmt.executeUpdate("INSERT INTO `Produits`(`nom`, `id`, `date_peremption`, `prix`, `quantite`) VALUES ('poisson', 2, '2016-03-17', 35, 1)");
		stmt.close();
		connection.close();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testListerProduits() {

		List<Produits> Produits = ProduitsDao.listerProduits();
		Assert.assertEquals(2, Produits.size());
		Assert.assertEquals("viande", Produits.get(0).getNom());
		Assert.assertEquals(1, Produits.get(0).getId());
		Assert.assertEquals(6, Produits.get(0).getDate().getMonth());
		Assert.assertEquals(2016-1900, Produits.get(0).getDate().getYear());
		Assert.assertEquals(5, Produits.get(0).getDate().getDay());
		Assert.assertEquals(15.6, Produits.get(0).getPrix(),0);
		Assert.assertEquals(3, Produits.get(0).getQuantite());
		Assert.assertEquals("poisson", Produits.get(1).getNom());
		Assert.assertEquals(2, Produits.get(1).getId());
		Assert.assertEquals(2, Produits.get(1).getDate().getMonth());
		Assert.assertEquals(2016-1900, Produits.get(1).getDate().getYear());
		Assert.assertEquals(4, Produits.get(1).getDate().getDay());
		Assert.assertEquals(35, Produits.get(1).getPrix(),0);
		Assert.assertEquals(1, Produits.get(1).getQuantite());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetProduits() {
		Produits Produits = ProduitsDao.getProduit(1);
		Assert.assertNotNull(Produits);
		Assert.assertEquals("viande",Produits.getNom());
		Assert.assertEquals(1, Produits.getId());
		Assert.assertEquals(6, Produits.getDate().getMonth());
		Assert.assertEquals(2016-1900, Produits.getDate().getYear());
		Assert.assertEquals(5, Produits.getDate().getDay());
		Assert.assertEquals(15.6, Produits.getPrix(),0);
		Assert.assertEquals(3, Produits.getQuantite());
	}
	
	@Test
	public void testGetProduitsSansResultat() {
		Produits Produits = ProduitsDao.getProduit(0);
		Assert.assertNull(Produits);		
	}
	
	@Test
	public void testAjouterProduits() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2016, Calendar.SEPTEMBER, 2, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Produits Produits = new Produits(3,"chips",50,cal.getTime(),4.9);
		Produits ProduitsAjoute = ProduitsDao.ajouterProduit(Produits);
		
		Assert.assertEquals(3, ProduitsAjoute.getId());
		Assert.assertEquals("chips", ProduitsAjoute.getNom());
		Assert.assertEquals(50, ProduitsAjoute.getQuantite());
		Assert.assertEquals(cal.getTime(), ProduitsAjoute.getDate());
		Assert.assertEquals(4.9, ProduitsAjoute.getPrix(),0);
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `produits` WHERE nom = ?");
		stmt.setString(1, ProduitsAjoute.getNom());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(ProduitsAjoute.getNom(),rs.getString("nom"));
		Assert.assertEquals(ProduitsAjoute.getId(),rs.getInt("id"));
		Assert.assertEquals(ProduitsAjoute.getQuantite(),rs.getDouble("quantite"),0);
		Assert.assertEquals(ProduitsAjoute.getPrix(),rs.getDouble("prix"),0);
		Assert.assertFalse(rs.next());
		connection.close();
	}
	
	@Test
	public void testSupprimerProduits() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2017, Calendar.JANUARY, 8, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Produits Produits = new Produits(3,"pain",8,cal.getTime(),2.9);
		ProduitsDao.ajouterProduit(Produits);
		
		int i = ProduitsDao.listerProduits().size();
		Assert.assertEquals(3,i);
		
		ProduitsDao.supprimerProduit(3);
		int j = ProduitsDao.listerProduits().size();
		Assert.assertEquals(2,j);
		Produits Produits2 = ProduitsDao.listerProduits().get(j-1);
		Assert.assertEquals("poisson", Produits2.getNom());
		Produits Produits3 = ProduitsDao.listerProduits().get(j-2);
		Assert.assertEquals("viande", Produits3.getNom());
		
	}
	
	@Test
	public void testUpdateProduit() throws Exception{

		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2016, Calendar.SEPTEMBER, 2, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Produits prod = new Produits(2,"poisson",50,cal.getTime(),4.9);
		ProduitsDao.majProduit(prod);
		int j = ProduitsDao.listerProduits().size();
		Assert.assertEquals(2,j);
		Produits Produits = ProduitsDao.listerProduits().get(j-1);
		Assert.assertEquals("poisson", Produits.getNom());
		Assert.assertEquals(4.9, Produits.getPrix(),0);
		Assert.assertEquals(50, Produits.getQuantite(),0);
		
	}
}