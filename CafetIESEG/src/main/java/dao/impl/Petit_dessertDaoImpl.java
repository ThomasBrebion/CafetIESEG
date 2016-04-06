package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Petit_dessert;
import dao.Petit_dessertDao;


public class Petit_dessertDaoImpl implements Petit_dessertDao {

	@Override
	public List<Petit_dessert> listerPetit_dessert() {
		List<Petit_dessert> listeDePetit_dessert = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM petit_dessert ORDER BY prix");
			while (resultSet.next()) {
				listeDePetit_dessert.add(new Petit_dessert(resultSet.getString("nom"), resultSet.getDouble("prix")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDePetit_dessert;
	}

	@Override
	public Petit_dessert getPetit_dessert(String nom) {
		Petit_dessert petit_dessert = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM petit_dessert WHERE nom = ?");
			stmt.setString(1, nom);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				petit_dessert = new Petit_dessert(resultSet.getString("nom"), resultSet.getDouble("prix"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petit_dessert;
	}

	@Override
	public Petit_dessert ajouterPetit_dessert(Petit_dessert petit_dessert) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `petit_dessert`(`nom`,`prix`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, petit_dessert.getNom());
			stmt.setDouble(2, petit_dessert.getPrix());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petit_dessert;
	}

	@Override
	public void supprimerPetit_dessert(String nom) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `petit_dessert` WHERE `nom`=?");
			stmt.setString(1, nom);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

}
