import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Entities.Utilisateur;
import dao.impl.DataSourceProvider;
import dao.impl.UtilisateurDaoImpl;
import dao.UtilisateurDao;

import org.junit.Assert;

public class UtilisateurDaoTestCase {
		
	private UtilisateurDao UtilisateurDao = new UtilisateurDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM `utilisateurs`");
		stmt.executeUpdate("INSERT INTO `utilisateurs` (`mail`, `mdp`, `id`) VALUES('cafetieseg@ieseg.fr', 'admin',1);");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerUtilisateur() {
		List<Utilisateur> Utilisateur = UtilisateurDao.listerUtilisateurs();
		Assert.assertEquals(1, Utilisateur.size());
		Assert.assertEquals(1, Utilisateur.get(0).getId());
		Assert.assertEquals("cafetieseg@ieseg.fr", Utilisateur.get(0).getMail());
		Assert.assertEquals("admin", Utilisateur.get(0).getMotDePasse());
	}
	
	@Test
	public void testGetUtilisateur() {
		Utilisateur Utilisateur = UtilisateurDao.getUtilisateur(1);
		Assert.assertNotNull(Utilisateur);
		Assert.assertEquals("cafetieseg@ieseg.fr", Utilisateur.getMail());
		Assert.assertEquals("admin", Utilisateur.getMotDePasse());
	}
	
	@Test
	public void testGetUtilisateurSansResultat() {
		Utilisateur Utilisateur = UtilisateurDao.getUtilisateur(0);
		Assert.assertNull(Utilisateur);		
	}
	
	@Test
	public void testAjouterUtilisateur() throws Exception {
		Utilisateur Utilisateur = new Utilisateur("motdepasse","toto@ieseg.com",2);
		Utilisateur UtilisateurAjoute = UtilisateurDao.ajouterUtilisateur(Utilisateur);
		
		Assert.assertEquals("toto@ieseg.com", UtilisateurAjoute.getMail());
		Assert.assertEquals("motdepasse", UtilisateurAjoute.getMotDePasse());
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `utilisateurs` WHERE id = ?");
		stmt.setInt(1, UtilisateurAjoute.getId());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(UtilisateurAjoute.getMail(),rs.getString("mail"));
		Assert.assertEquals(UtilisateurAjoute.getMotDePasse(),rs.getString("mdp"));
		Assert.assertFalse(rs.next());
		UtilisateurDao.supprimerUtilisateur(2);
		connection.close();
	}
	
	@Test
	public void testSupprimerUtilisateur() throws Exception {
		Utilisateur utilisateur = new Utilisateur("motdepasse","toto@ieseg.com",2);
		UtilisateurDao.ajouterUtilisateur(utilisateur);
		
		int i = UtilisateurDao.listerUtilisateurs().size();
		Assert.assertEquals(2,i);
		
		UtilisateurDao.supprimerUtilisateur(2);
		int j = UtilisateurDao.listerUtilisateurs().size();
		Assert.assertEquals(1,j);
		Utilisateur utilisateur1 = UtilisateurDao.listerUtilisateurs().get(j-1);
		Assert.assertEquals("cafetieseg@ieseg.fr", utilisateur1.getMail());
		
	}
}