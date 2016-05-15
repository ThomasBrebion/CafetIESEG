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
				listeDeUtilisateur.add(new Utilisateur(resultSet.getString("mdp"),resultSet.getString("mail"), resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeUtilisateur;
	}

	@Override
	public Utilisateur getUtilisateur(int id) {
		Utilisateur utilisateur = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM utilisateurs WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				utilisateur = new Utilisateur(resultSet.getString("mdp"), resultSet.getString("mail"), resultSet.getInt("id"));
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
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `utilisateurs`(`mail`,`mdp`,`id`)VALUES(?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getMail());
			stmt.setString(2, utilisateur.getMotDePasse());
			stmt.setInt(3, utilisateur.getId());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	@Override
	public void majUtilisateur(Utilisateur utilisateur) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `utilisateurs` SET `mail`=?,`mdp`=? WHERE `id`=?");
			stmt.setString(1, utilisateur.getMail());
			stmt.setString(2, utilisateur.getMotDePasse());
			stmt.setInt(3, utilisateur.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void supprimerUtilisateur(int id) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `utilisateurs` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}		
		}

}
