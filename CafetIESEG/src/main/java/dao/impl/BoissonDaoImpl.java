package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Boissons;
import dao.BoissonsDao;


public class BoissonDaoImpl implements BoissonsDao {

	@Override
	public List<Boissons> listerBoissons() {
		List<Boissons> listeDeBoisson = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM boissons ORDER BY prix");
			while (resultSet.next()) {
				listeDeBoisson.add(new Boissons(resultSet.getString("nom"), resultSet.getDouble("prix")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeBoisson;
	}

	@Override
	public Boissons getBoissons(String nom) {
		Boissons boisson = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM boissons WHERE nom = ?");
			stmt.setString(1, nom);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				boisson = new Boissons(resultSet.getString("nom"), resultSet.getDouble("prix"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boisson;
	}

	@Override
	public Boissons ajouterBoissons(Boissons boisson) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `boissons`(`nom`,`prix`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, boisson.getNom());
			stmt.setDouble(2, boisson.getPrix());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boisson;
	}

	@Override
	public void supprimerBoissons(String nom) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `boissons` WHERE `nom`=?");
			stmt.setString(1, nom);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

}
