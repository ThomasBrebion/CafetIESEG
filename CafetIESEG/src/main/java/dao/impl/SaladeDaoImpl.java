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
	public Salades ajouterSalade(Salades salades) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `salade`(`nom`,`prix_solo`,`prix_menu`,`id`)VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, salades.getNom());
			stmt.setDouble(2, salades.getPrix_solo());
			stmt.setDouble(3, salades.getPrix_menu());
			stmt.setInt(4, salades.getId());
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
	public void supprimerSalade(int id) {
		
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
