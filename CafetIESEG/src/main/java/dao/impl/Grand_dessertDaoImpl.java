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

/* Méthode pour les grands desserts */

public class Grand_dessertDaoImpl implements Grand_dessertDao {

	@Override
	public List<Grand_dessert> listerGrand_dessert() {
		
		/* Cette méthode permet de lister tous les grands desserts, elle est utilisée pour la carte de la Cafet */
		
		List<Grand_dessert> listeDeGrand_dessert = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM grand_dessert ORDER BY prix");
			while (resultSet.next()) {
				listeDeGrand_dessert.add(new Grand_dessert(resultSet.getString("nom"), resultSet.getDouble("prix"),resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeGrand_dessert;
	}

	@Override
	public Grand_dessert getGrand_dessert(int id) {
		
		/* Cette méthode permet de sélectionner un grand dessert grâce à son Id */
		
		Grand_dessert grand_dessert = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM grand_dessert WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				grand_dessert = new Grand_dessert(resultSet.getString("nom"), resultSet.getDouble("prix"),resultSet.getInt("id"));
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
		
		/* Cette méthode permet d'ajouter un grand dessert, elle est utilisée pour la carte de la Cafet */
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `grand_dessert`(`nom`,`prix`,`id`)VALUES(?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, grand_dessert.getNom());
			stmt.setDouble(2, grand_dessert.getPrix());
			stmt.setInt(3, grand_dessert.getId());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grand_dessert;
	}

	@Override
	public void supprimerGrand_dessert(int id) {
		
		/* Cette méthode permet de supprimer un grand dessert, elle est utilisée pour la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `grand_dessert` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

	@Override
	public void majGrand_dessert(Grand_dessert grand_dessert) {
		
		/* Cette méthode permet de modifier un grand dessert, elle est utilisée pour la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `grand_dessert` SET nom = ?, prix = ? WHERE `id`=?");
			stmt.setString(1, grand_dessert.getNom());
			stmt.setDouble(2, grand_dessert.getPrix());
			stmt.setInt(3, grand_dessert.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
