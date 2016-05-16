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

/* Méthodes pour les articles */

public class BoissonDaoImpl implements BoissonsDao {
	
	/* Cette méthode permet de lister toutes les boissons, elle est utilisée pour la carte de la Cafet */
	
	@Override
	public List<Boissons> listerBoissons() {
		List<Boissons> listeDeBoisson = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM boissons ORDER BY prix");
			while (resultSet.next()) {
				listeDeBoisson.add(new Boissons(resultSet.getString("nom"), resultSet.getDouble("prix"), resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeBoisson;
	}

	@Override
	public Boissons getBoissons(int id) {
		
		/* Cette méthode permet de sélectionner une boisson grâce à son ID */
		
		Boissons boisson = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM boissons WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				boisson = new Boissons(resultSet.getString("nom"), resultSet.getDouble("prix"), resultSet.getInt("id"));
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
		
		/* Cette méthode permet d'ajouter une boisson, elle est utilisée pour la carte de la Cafet */
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `boissons`(`nom`,`prix`,`id`)VALUES(?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, boisson.getNom());
			stmt.setDouble(2, boisson.getPrix());
			stmt.setInt(3, boisson.getId());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boisson;
	}

	@Override
	public void supprimerBoissons(int id) {
		
		/* Cette méthode permet de supprimer une boisson, elle est utilisée pour la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `boissons` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}	
	

	@Override
	public void majBoissons(Boissons boissons) {
		
		/* Cette méthode permet de modifier une boisson, elle est utilisée pour la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `boissons` SET nom = ?, prix = ? WHERE `id`=?");
			stmt.setString(1, boissons.getNom());
			stmt.setDouble(2, boissons.getPrix());
			stmt.setInt(3, boissons.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
