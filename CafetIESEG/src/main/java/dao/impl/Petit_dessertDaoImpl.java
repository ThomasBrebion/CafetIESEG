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

/* Méthode pour les petits desserts */

public class Petit_dessertDaoImpl implements Petit_dessertDao {

	@Override
	public List<Petit_dessert> listerPetit_dessert() {
		
		/* Cette méthode permet de lister tous les petits desserts, elle est utilisée pour la carte de la Cafet */
		
		List<Petit_dessert> listeDePetit_dessert = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM petit_dessert ORDER BY prix");
			while (resultSet.next()) {
				listeDePetit_dessert.add(new Petit_dessert(resultSet.getString("nom"), resultSet.getDouble("prix"),resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDePetit_dessert;
	}

	@Override
	public Petit_dessert getPetit_dessert(int id) {
		
		/* Cette méthode permet de sélectionner un petit dessert grâce à son Id */
		
		Petit_dessert petit_dessert = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM petit_dessert WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				petit_dessert = new Petit_dessert(resultSet.getString("nom"), resultSet.getDouble("prix"),resultSet.getInt("id"));
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
		
		/* Cette méthode permet d'ajouter un petit dessert, elle est utilisée pour la carte de la Cafet */
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `petit_dessert`(`nom`,`prix`,`id`)VALUES(?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, petit_dessert.getNom());
			stmt.setDouble(2, petit_dessert.getPrix());
			stmt.setDouble(3, petit_dessert.getId());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petit_dessert;
	}

	@Override
	public void supprimerPetit_dessert(int id) {
		
		/* Cette méthode permet de supprimer un petit dessert, elle est utilisée pour la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `petit_dessert` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

	@Override
	public void majPetit_dessert(Petit_dessert petit_dessert) {
		
		/* Cette méthode permet de modifier un petit dessert, elle est utilisée pour la carte de la Cafet */
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `petit_dessert` SET nom = ?, prix = ? WHERE `id`=?");
			stmt.setString(1, petit_dessert.getNom());
			stmt.setDouble(2, petit_dessert.getPrix());
			stmt.setInt(3, petit_dessert.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
