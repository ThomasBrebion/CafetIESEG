package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Salades;
import dao.SaladesDao;

/* Méthode pour les salades */

public class SaladeDaoImpl implements SaladesDao {

	@Override
	public List<Salades> listerSalades() {
		
		/* Cette méthode permet de lister toutes les salades dans la carte de la Cafet */
		
		List<Salades> listeDeSalades = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM salade ORDER BY prix_solo");
			while (resultSet.next()) {
				listeDeSalades.add(new Salades(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"), resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeSalades;
	}

	@Override
	public Salades getSalade(int id) {
		
		/* Cette méthode permet de sélectionner une salade en fonction de son id */
		
		Salades salades = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM salade WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				salades = new Salades(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"), resultSet.getInt("id"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salades;
	}

	@Override
	public Salades ajouterSalade(Salades salade) {
		
		/* Cette méthode permet d'ajouter une salade dans la carte de la cafet */
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `salade`(`nom`,`prix_solo`,`prix_menu`,`id`)VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, salade.getNom());
			stmt.setDouble(2, salade.getPrix_solo());
			stmt.setDouble(3, salade.getPrix_menu());
			stmt.setInt(4, salade.getId());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `plat`(`nom`,`prix`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, salade.getNom());
			stmt.setDouble(2, salade.getPrix_menu());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salade;
	}

	@Override
	public void supprimerSalade(int id) {
		
		/* Cette méthode permet de supprimer une salade dans la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `salade` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

	@Override
	public void majSalade(Salades salade) {
		
		/* Cette méthode permet de modifier une salade dans la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `salade` SET `nom`=?,`prix_solo`=?,`prix_menu`=? WHERE `id`=?");
			stmt.setString(1, salade.getNom());
			stmt.setDouble(2, salade.getPrix_solo());
			stmt.setDouble(3, salade.getPrix_menu());
			stmt.setInt(4, salade.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
