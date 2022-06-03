package model;

import java.util.ArrayList;

import application.controller.CheckCombinazioni;

public class Combinazione {
	
	private ArrayList<Card> combinazione;
	private int punteggio = 0;
	private boolean tris = false;
	
	public Combinazione() {}
	
	public void setCombinazione(ArrayList<Card> combinazione) {
		this.combinazione = combinazione;
		calcolaPunteggio();
		
	}
	
	public void addCartaCombinazione(Card carta) {
		this.combinazione.add(carta);	
	}
	
	public void calcolaPunteggio () {
		

		for (int i=0; i<combinazione.size(); i++) {
			
			int valore = combinazione.get(i).getValore();
			
			if (valore >= 11)
				valore=10;

			if (tris) {
				if (valore == 1) {
					valore = 11;
				} 
			} else {
				if (i>0 && valore==1 && combinazione.get(i-1).getValore()==13) {
					valore = 11;
				}
								
			}
			
			punteggio += valore;

		}
		
		
	}
	
	public void setTris(boolean tris) {
		this.tris = tris;
	}
	
	public boolean isTris() {
		return tris;
	}

	
	public int getPunteggio() {
		return punteggio;
	}
	
	public ArrayList<Card> getCombinazione() {
		return combinazione;
	}
	
	public boolean haJolly () {
		for (Card c: combinazione) {
			if (c.getSeme().equals("Jolly"))
				return true;
		}
		
		return false;
	}
	


}
