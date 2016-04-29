package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Plat_chaud;
import dao.Plat_chaudDao;


public class Plat_chaudDaoImpl implements Plat_chaudDao {

	@Override
	public List<Plat_chaud> listerPlat_chaud() {
		List<Plat_chaud> listeDePlat_chaud = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM plat_chaud ORDER BY prix_solo");
			while (resultSet.next()) {
				listeDePlat_chaud.add(new Plat_chaud(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"),resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDePlat_chaud;
	}

	@Override
	public Plat_chaud getPlat_chaud(int id) {
		Plat_chaud plat_chaud = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM plat_chaud WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				plat_chaud = new Plat_chaud(resultSet.getString("nom"), resultSet.getDouble("prix_solo"), resultSet.getDouble("prix_menu"),resultSet.getInt("id"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plat_chaud;
	}

	@Override
	public Plat_chaud ajouterPlat_chaud(Plat_chaud plat_chaud) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `plat_chaud`(`nom`,`prix_solo`,`prix_menu`,`id`)VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, plat_chaud.getNom());
			stmt.setDouble(2, plat_chaud.getPrix_solo());
			stmt.setDouble(3, plat_chaud.getPrix_menu());
			stmt.setDouble(4, plat_chaud.getId());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `plat`(`nom`,`prix`)VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, plat_chaud.getNom());
			stmt.setDouble(2, plat_chaud.getPrix_menu());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plat_chaud;
	}

	@Override
	public void supprimerPlat_chaud(int id) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `plat_chaud` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
				
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		}

	@Override
	public void majPlat_chaud(Plat_chaud plat_chaud) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `plat_chaud` SET nom = ?, prix_solo = ?, prix_menu = ? WHERE `id`=?");
			stmt.setString(1, plat_chaud.getNom());
			stmt.setDouble(2, plat_chaud.getPrix_solo());
			stmt.setDouble(3, plat_chaud.getPrix_menu());
			stmt.setInt(4, plat_chaud.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}