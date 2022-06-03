package model;

import java.util.ArrayList;
import java.util.Collections;

public class Mazzo {

	private ArrayList<Card> mazzo;
	public static ArrayList<Card> mazzoFisso;
	
	
	public Mazzo() {
		mazzo = new ArrayList<Card>();
		mazzoFisso = new ArrayList<Card>();
		
		
		if (Utilities.mazzoTruccato) {
				
		}
		
		
		int c=0;
		
		
		for(String s : Utilities.SEMI) {
			for(int j = 1 ; j < 14 ; j++) {
				Card carta = new Card(s, j, c);
				Card carta2 = new Card(s, j, c+54);
				c++;
				mazzo.add(carta);
				mazzo.add(carta2);
				mazzoFisso.add(carta);
				mazzoFisso.add(carta2);
			}
		}
		
		mazzo.add(new Card("Jolly", 14,179));
		mazzo.add(new Card("Jolly", 14,180));
		mazzo.add(new Card("Jolly", 14,190));
		mazzo.add(new Card("Jolly", 14,220));
		
		mazzoFisso.add(new Card("Jolly", 14,179));
		mazzoFisso.add(new Card("Jolly", 14,180));
		mazzoFisso.add(new Card("Jolly", 14,190));
		mazzoFisso.add(new Card("Jolly", 14,220));
	}
	
	public void shuffle() {
		Collections.shuffle(mazzo);
	}
	

	
	public void print() {
		for(Card c : mazzo) {
			System.out.println(c);
		}
	}
	
	public Card estraiCarta() {
		Card card = mazzo.get(0);
		mazzo.remove(0);
		return card;
		
	}
	
	public static Card getById(int id) {
		for (Card c: mazzoFisso) {
			if (c.getId()==id)
				return c;
		}
		System.out.println("ID CERCATO" + id );
		for (Card c: mazzoFisso) {
			if (c.getId()==id)
				System.out.println("ID PRESENTE");
		}
		return null;
	}
	
}