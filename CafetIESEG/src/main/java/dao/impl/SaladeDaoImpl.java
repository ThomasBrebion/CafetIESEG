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


public class SaladeDaoImpl implements SaladesDao {

	@Override
	public List<Salades> listerSalades() {
		List<Salades> listeDeSalades = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM salade ORDER BY prix_solo");
			while (resultSet.next()) {
				listeDeSalades.add(new Salades(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeSalades;
	}

	@Override
	public Salades getSalade(String nom) {
		Salades salades = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM salade WHERE nom = ?");
			stmt.setString(1, nom);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				salades = new Salades(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salades;
	}

	@Override
	public Salades ajouterSalade(Salades salades) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `salade`(`nom`,`prix_solo`,`prix_menu`)VALUES(?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, salades.getNom());
			stmt.setDouble(2, salades.getPrix_solo());
			stmt.setDouble(3, salades.getPrix_menu());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `plat`(`nom`,`prix`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, salades.getNom());
			stmt.setDouble(2, salades.getPrix_menu());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salades;
	}

	@Override
	public void supprimerSalade(String nom) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `salade` WHERE `nom`=?");
			stmt.setString(1, nom);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

}
