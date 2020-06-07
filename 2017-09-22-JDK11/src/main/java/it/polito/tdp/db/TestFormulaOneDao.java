package it.polito.tdp.db;

import java.util.List;

import it.polito.tdp.model.Season;



public class TestFormulaOneDao {

	public static void main(String[] args) {
		FormulaOneDAO dao = new FormulaOneDAO();

		List<Season> seasons = dao.getAllSeasons();
		System.out.println(seasons);

	}

}
