package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;

import Entities.Produits;
import dao.ProduitsDao;

public class ProduitsDaoImpl implements ProduitsDao {
	
	@Override
	public List<Produits> listerProduits() {
		List<Produits> listeDeProduits = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM produits ORDER BY id");
			while (resultSet.next()) {
				Produits produit = new Produits(resultSet.getInt("id"), resultSet.getString("nom"),resultSet.getInt("quantite"),
						resultSet.getDate("date_peremption"), resultSet.getDouble("prix"));
						produit.setDays_left(Days.daysBetween(new Instant(), new DateTime(produit.getDate())).getDays());
						listeDeProduits.add(produit);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDeProduits;
	}

	@Override
	public Produits getProduit(Integer id) {
		Produits produits = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produits WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				produits = new Produits(resultSet.getInt("id"), resultSet.getString("nom"),resultSet.getInt("quantite"),
						resultSet.getDate("date_peremption"), resultSet.getDouble("prix"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}

	@Override
	public Produits ajouterProduit(Produits produits) {
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `produits`(`id`,`nom`,`quantite`,`date_peremption`,`prix`)VALUES(?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, produits.getId());
			stmt.setString(2, produits.getNom());
			stmt.setInt(3, produits.getQuantite());
			stmt.setDate(4, new Date(produits.getDate().getTime()));
			stmt.setDouble(5, produits.getPrix());
			stmt.executeUpdate();
			
			ResultSet ids = stmt.getGeneratedKeys();
			if(ids.next()) {
				produits.setId(ids.getInt(1));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}
	
	@Override
	public void supprimerProduit(int id) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM `produits` WHERE `id`=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void majProduit(Produits produit) {
		
		try{
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE `produits` SET nom = ?, date_peremption = ?, quantite = ?, prix = ? WHERE `id`=?");
			stmt.setString(1, produit.getNom());
			stmt.setDate(2, new java.sql.Date(produit.getDate().getTime()));
			stmt.setInt(3, produit.getQuantite());
			stmt.setDouble(4, produit.getPrix());
			stmt.setInt(5, produit.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
}
