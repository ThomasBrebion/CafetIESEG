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
		stmt.executeUpdate("INSERT INTO `utilisateurs` (`id`, `mdp`) VALUES('admin', 'admin');");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerUtilisateur() {
		List<Utilisateur> Utilisateur = UtilisateurDao.listerUtilisateurs();
		Assert.assertEquals(1, Utilisateur.size());
		Assert.assertEquals("admin", Utilisateur.get(0).getIdentifiant());
		Assert.assertEquals("admin", Utilisateur.get(0).getMotDePasse());
	}
	
	@Test
	public void testGetUtilisateur() {
		Utilisateur Utilisateur = UtilisateurDao.getUtilisateur("admin");
		Assert.assertNotNull(Utilisateur);
		Assert.assertEquals("admin", Utilisateur.getIdentifiant());
		Assert.assertEquals("admin", Utilisateur.getMotDePasse());
	}
	
	@Test
	public void testGetUtilisateurSansResultat() {
		Utilisateur Utilisateur = UtilisateurDao.getUtilisateur("");
		Assert.assertNull(Utilisateur);		
	}
	
	@Test
	public void testAjouterUtilisateur() throws Exception {
		Utilisateur Utilisateur = new Utilisateur("motdepasse","toto");
		Utilisateur UtilisateurAjoute = UtilisateurDao.ajouterUtilisateur(Utilisateur);
		
		Assert.assertEquals("toto", UtilisateurAjoute.getIdentifiant());
		Assert.assertEquals("motdepasse", UtilisateurAjoute.getMotDePasse());
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `utilisateurs` WHERE id = ?");
		stmt.setString(1, UtilisateurAjoute.getIdentifiant());
		ResultSet rs = stmt.executeQuery();
		Assert.assertTrue(rs.next());
		Assert.assertEquals(UtilisateurAjoute.getIdentifiant(),rs.getString("id"));
		Assert.assertEquals(UtilisateurAjoute.getMotDePasse(),rs.getString("mdp"));
		Assert.assertFalse(rs.next());
		connection.close();
	}
}