package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Plat;
import dao.PlatDao;


public class PlatDaoImpl implements PlatDao {

	@Override
	public List<Plat> listerPlat() {
		List<Plat> listeDePlat = new ArrayList<>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM plat_chaud ORDER BY prix_solo");
			while (resultSet.next()) {
				listeDePlat.add(new Plat(resultSet.getString("nom"), resultSet.getDouble("prix")));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDePlat;
	}

	@Override
	public Plat getPlat(String nom) {
		Plat plat = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM plat WHERE nom = ?");
			stmt.setString(1, nom);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				plat = new Plat(resultSet.getString("nom"), resultSet.getDouble("prix"));
			}			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plat;
	}

}
