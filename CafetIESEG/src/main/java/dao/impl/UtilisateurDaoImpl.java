package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Utilisateur;
import dao.UtilisateurDao;


public class UtilisateurDaoImpl implements UtilisateurDao {

	@Override
	public List<Utilisateur> listerUtilisateurs() {
		List<Utilisateur> listeDeUtilisateur = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM utilisateurs");
			while (resultSet.next()) {
				listeDeUtilisateur.add(new Utilisateur(resultSet.getString("mdp"), resultSet.getString("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeUtilisateur;
	}

	@Override
	public Utilisateur getUtilisateur(String id) {
		Utilisateur utilisateur = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM utilisateurs WHERE id = ?");
			stmt.setString(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				utilisateur = new Utilisateur(resultSet.getString("mdp"), resultSet.getString("id"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `utilisateurs`(`id`,`mdp`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getIdentifiant());
			stmt.setString(2, utilisateur.getMotDePasse());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

}
