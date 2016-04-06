package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Sandwich;
import dao.SandwichDao;


public class SandwichDaoImpl implements SandwichDao {

	@Override
	public List<Sandwich> listerSandwichs() {
		List<Sandwich> listeDeSandwich = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM sandwich ORDER BY prix_solo");
			while (resultSet.next()) {
				listeDeSandwich.add(new Sandwich(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeSandwich;
	}

	@Override
	public Sandwich getSandwich(String nom) {
		Sandwich sandwich = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sandwich WHERE nom = ?");
			stmt.setString(1, nom);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				sandwich = new Sandwich(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sandwich;
	}

	@Override
	public Sandwich ajouterSandwich(Sandwich sandwich) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `sandwich`(`nom`,`prix_solo`,`prix_menu`)VALUES(?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, sandwich.getNom());
			stmt.setDouble(2, sandwich.getPrix_solo());
			stmt.setDouble(3, sandwich.getPrix_menu());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `plat`(`nom`,`prix`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, sandwich.getNom());
			stmt.setDouble(2, sandwich.getPrix_menu());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sandwich;
	}

	@Override
	public void supprimerSandwich(String nom) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `sandwich` WHERE `nom`=?");
			stmt.setString(1, nom);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

}
