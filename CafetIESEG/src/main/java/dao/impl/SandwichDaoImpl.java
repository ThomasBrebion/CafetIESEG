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
				listeDeSandwich.add(new Sandwich(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"),resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeSandwich;
	}

	@Override
	public Sandwich getSandwich(int id) {
		Sandwich sandwich = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sandwich WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				sandwich = new Sandwich(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"),resultSet.getInt("id"));
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
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `sandwich`(`nom`,`prix_solo`,`prix_menu`,`id`)VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, sandwich.getNom());
			stmt.setDouble(2, sandwich.getPrix_solo());
			stmt.setDouble(3, sandwich.getPrix_menu());
			stmt.setInt(4, sandwich.getId());
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
	public void supprimerSandwich(int id) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `sandwich` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

	@Override
	public void majSandwich(Sandwich sandwich) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `sandwich` SET nom = ?, prix_solo = ?, prix_menu = ? WHERE `id`=?");
			stmt.setString(1, sandwich.getNom());
			stmt.setDouble(2, sandwich.getPrix_solo());
			stmt.setDouble(3, sandwich.getPrix_menu());
			stmt.setInt(4, sandwich.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
