package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.db.FormulaOneDAO;

public class Model {

	private FormulaOneDAO dao ;
	private Map<Integer,Race> idMap;
	private Graph<Race, DefaultWeightedEdge> grafo;
	private List<Adiacenza> adiacenze;
	public Model() {
		dao = new FormulaOneDAO();
		idMap= new HashMap<>();
		
	}
	
	public List<Integer> getAnni(){
		return dao.getAllYears();
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(idMap, anno));
		
		adiacenze = this.dao.getAdiacenze(idMap, anno);
		for(Adiacenza a : adiacenze) {
			if(this.grafo.containsVertex(a.getR1()) && this.grafo.containsVertex(a.getR2()) &&
					this.grafo.getEdge(a.getR1(), a.getR2()) == null) {
				Graphs.addEdge(this.grafo, a.getR1(), a.getR2(), a.getPeso());
				
			}
		}
		idMap.clear();
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
		}

		public int nArchi() {
		return this.grafo.edgeSet().size();
		}
		
		public Adiacenza getArcoMax() {
			Adiacenza massimo = null;
	        Integer	peso =0;
	        
			for(Adiacenza a : adiacenze) {
			
				Integer nuovoPeso= (int) this.grafo.getEdgeWeight(this.grafo.getEdge(a.getR1(), a.getR2()));
			if(nuovoPeso > peso) {
				massimo = a;
				peso = nuovoPeso;
			}
			}
			return massimo;
		}
		public List<Adiacenza> getMassimi(){
		List<Adiacenza> massimi = new ArrayList<Adiacenza>();	
		Adiacenza massimo = getArcoMax();
		
		Integer pesoMax = (int)this.grafo.getEdgeWeight(this.grafo.getEdge(massimo.getR1(), massimo.getR2()));
			for(Adiacenza a : adiacenze) {
				Integer nuovoPeso= (int) this.grafo.getEdgeWeight(this.grafo.getEdge(a.getR1(), a.getR2()));
				if(nuovoPeso == pesoMax ) {
					massimi.add(a);
				}
				
		}
			return massimi;
      }
     }
