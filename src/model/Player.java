package model;

import java.util.ArrayList;

public class Player {

	private String nome;
	private ArrayList<Card> carteInMano;
	private boolean calato;

	
	public Player() {
		this.carteInMano = new ArrayList<Card>();
		this.calato = false;
	}
	
	public void addCartaInMano(Card carta) {
		carteInMano.add(carta);
	}
	
	public ArrayList<Card> getCarteInMano(){
		return carteInMano;
	}
	
	public void removeInMano(Card c) {
		carteInMano.remove(c);
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void playerCalato () {
		this.calato = true;
	}
	
	public boolean isCalato () {
		return calato;
	}
	
	public Card getById (int id) {
		for (Card c: carteInMano) {
			if (c.getId()==id)
				return c;
		}
		
		return null;
	}
	
	
	public boolean haVinto() {
		if (carteInMano.size()==0)
			return true;
		return false;
	}
	
	public void stampaCarteInMano() {
		Utilities.stampaInfo("GIOCATORE -> " + nome + " <- hai le seguenti carte:");
		String carte = "";
		
		Utilities.carteOrdinatePerValore(carteInMano);
		

		
		String numeri = "   ";
		
		for (int i=0; i<carteInMano.size(); i++) {
			Card carta = carteInMano.get(i);
			carte += carta;
			if (i<carteInMano.size()-1)
				carte += " - ";
			numeri += i+1;
			if(i<9)
				numeri+= "ª       ";
			else
				numeri+= "ª      ";
				
			
		}
		
		System.out.println("");
		System.out.println(numeri);
		System.out.println(carte);
		System.out.println("");

	}
	
	public void stampaCompatta() {
		String carte = "";
		
		Utilities.carteOrdinatePerValore(carteInMano);
		

		
		String numeri = "   ";
		
		for (int i=0; i<carteInMano.size(); i++) {
			Card carta = carteInMano.get(i);
			carte += carta;
			if (i<carteInMano.size()-1)
				carte += " - ";
			numeri += i+1;
			if(i<9)
				numeri+= "ª       ";
			else
				numeri+= "ª      ";
				
			
		}
		
		System.out.println("");
		System.out.println(numeri);
		System.out.println(carte);
		System.out.println("");
	}
	
	
	
	
}
