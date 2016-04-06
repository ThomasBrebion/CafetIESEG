package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Grand_dessert;
import dao.Grand_dessertDao;


public class Grand_dessertDaoImpl implements Grand_dessertDao {

	@Override
	public List<Grand_dessert> listerGrand_dessert() {
		List<Grand_dessert> listeDeGrand_dessert = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM grand_dessert ORDER BY prix");
			while (resultSet.next()) {
				listeDeGrand_dessert.add(new Grand_dessert(resultSet.getString("nom"), resultSet.getDouble("prix")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeGrand_dessert;
	}

	@Override
	public Grand_dessert getGrand_dessert(String nom) {
		Grand_dessert grand_dessert = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM grand_dessert WHERE nom = ?");
			stmt.setString(1, nom);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				grand_dessert = new Grand_dessert(resultSet.getString("nom"), resultSet.getDouble("prix"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grand_dessert;
	}

	@Override
	public Grand_dessert ajouterGrand_dessert(Grand_dessert grand_dessert) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `grand_dessert`(`nom`,`prix`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, grand_dessert.getNom());
			stmt.setDouble(2, grand_dessert.getPrix());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grand_dessert;
	}

	@Override
	public void supprimerGrand_dessert(String nom) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `grand_dessert` WHERE `nom`=?");
			stmt.setString(1, nom);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

}
