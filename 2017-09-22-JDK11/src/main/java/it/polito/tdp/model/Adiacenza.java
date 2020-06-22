package it.polito.tdp.model;

public class Adiacenza {
 
 Race r1;
 Race r2;
 Integer peso;

public Adiacenza(Race r1, Race r2, Integer peso) {
	super();
	this.r1 = r1;
	this.r2 = r2;
	this.peso = peso;
}

public Race getR1() {
	return r1;
}

public void setR1(Race r1) {
	this.r1 = r1;
}

public Race getR2() {
	return r2;
}

public void setR2(Race r2) {
	this.r2 = r2;
}

public Integer getPeso() {
	return peso;
}

public void setPeso(Integer peso) {
	this.peso = peso;
}
 
 
}
