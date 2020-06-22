package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.model.Adiacenza;
import it.polito.tdp.model.Race;
import it.polito.tdp.model.Season;



public class FormulaOneDAO {

	public List<Season> getAllSeasons() {
		String sql = "SELECT year, url FROM seasons ORDER BY year";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Season> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Season(rs.getInt("year"), rs.getString("url")));
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List <Integer> getAllYears (){
		String sql= "SELECT DISTINCT `year` as anno " + 
				"FROM seasons";
		List<Integer> anni= new ArrayList<Integer>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				anni.add(rs.getInt("anno"));
		    
			}

			conn.close();
			return anni;
		}catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	}
	public List<Race> getVertici(Map<Integer,Race> idMap, Integer anno){
		String sql= "SELECT  * " + 
				"FROM races " + 
				"WHERE `year`=?";
		List<Race> gare = new ArrayList<Race>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("raceId"))) {
					Race r = new Race (rs.getInt("raceId"), rs.getInt("year"), rs.getInt("round"), rs.getInt("circuitId"),
							rs.getString("name"), rs.getDate("date").toLocalDate(), rs.getTime("time").toLocalTime(),rs.getString("url") );
					idMap.put(rs.getInt("raceId"), r);
					gare.add(r);
				}
				else {
					gare.add(idMap.get(rs.getInt("raceId")));
				}
			}
			conn.close();
			return gare;
		
	}catch (SQLException e) {
		e.printStackTrace();
		return null;}
		}
	public List<Adiacenza> getAdiacenze(Map<Integer,Race> idMap, Integer anno){
		final String sql= "SELECT r1.raceId AS gara1, r2.raceId AS gara2, COUNT(*) AS peso " + 
				"FROM drivers d, results r1, results r2, races ra1, races ra2 " + 
				"WHERE d.driverId=r1.driverId AND r2.driverId = d.driverId AND r1.statusId =1 AND r2.statusId=1 " + 
				"AND ra1.raceId = r1.raceId AND ra2.raceId = r2.raceId AND ra1.year=? AND ra2.year=? " + 
				"AND r1.raceId>r2.raceId " + 
				"GROUP BY r1.raceId, r2.raceId";
		
		List<Adiacenza> adiacenze = new ArrayList<Adiacenza>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, anno);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				adiacenze.add(new Adiacenza(idMap.get(rs.getInt("gara1")),
						                     idMap.get(rs.getInt("gara2")),
						                     rs.getInt("peso")));
				
			}
			conn.close();
			return adiacenze;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;}
			}
		
	}


