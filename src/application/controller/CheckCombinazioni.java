package application.controller;

import java.util.ArrayList;

import model.Card;
import model.Utilities;

public class CheckCombinazioni {
	
	public CheckCombinazioni() {
		
	}
	
	public static boolean isTris (ArrayList<Card> card) {
		
		if (card.size()!=3)
			return false;
		
		if (presenteJolly(card)) {
			ArrayList<Card> senzaJolly = combSenzaJolly(card);
			
			if ((senzaJolly.get(0).getValore()==senzaJolly.get(1).getValore()) && (senzaJolly.get(0).getSeme()!=senzaJolly.get(1).getSeme())) {
				for (Card c: card) {
					if (c.getSeme().equals("Jolly")) {
						c.setValore(senzaJolly.get(0).getValore());
					}
				}
				return true;
			}
			else 
				return false;
			
		}
		
		if (card.get(0).getValore()!=card.get(1).getValore() || card.get(0).getValore()!=card.get(2).getValore() || card.get(1).getValore()!=card.get(2).getValore())
			return false;
		
		if (card.get(0).getSeme().equals(card.get(1).getSeme()) || card.get(0).getSeme().equals(card.get(2).getSeme()) || card.get(1).getSeme().equals(card.get(2).getSeme()))
			return false;
		
		
		return true;
		
	}	
	
	public static boolean isQuatris (ArrayList<Card> card) {
		
		if (card.size()!=4)
			return false;
		
		if (presenteJolly(card)) {
			if (isTris(combSenzaJolly(card))) {
				
				for (Card c: card) {
					if (c.getSeme().equals("Jolly")) {
						c.setValore(combSenzaJolly(card).get(0).getValore());
					}
					
				}
				return true;

			}
			else
				return false;
		}
		
		if (card.get(0).getValore()!=card.get(1).getValore() || card.get(0).getValore()!=card.get(2).getValore() || card.get(0).getValore()!=card.get(3).getValore() || card.get(1).getValore()!=card.get(2).getValore()  || card.get(1).getValore()!=card.get(3).getValore()  || card.get(2).getValore()!=card.get(3).getValore())
			return false;
		
		if (card.get(0).getSeme().equals(card.get(1).getSeme()) || card.get(0).getSeme().equals(card.get(2).getSeme())  || card.get(0).getSeme().equals(card.get(3).getSeme()) || card.get(1).getSeme().equals(card.get(2).getSeme()) || card.get(1).getSeme().equals(card.get(3).getSeme())  || card.get(2).getSeme().equals(card.get(3).getSeme()) )
			return false;
		
		
		return true;
		
	}
	
	public static boolean isScala (ArrayList<Card> card) {
		

		ArrayList<Card> cardCopy = copia(card);

		
		if (cardCopy.size()<3)
			return false;

		
		int contJolly = 0;
		int limite = 0;
		
		if (cardCopy.get(0).getSeme().equals("Jolly") || cardCopy.get(cardCopy.size()-1).getSeme().equals("Jolly"))
			limite = 2;
		else
			limite = 3;
		
		boolean controllatoJolly = false;
		
		
		for (int i=0; i<cardCopy.size()-1; i++) {
			if (!(cardCopy.get(i).getSeme().equals(cardCopy.get(i+1).getSeme()))) {
				if (cardCopy.get(i).getSeme().equals("Jolly") || cardCopy.get(i+1).getSeme().equals("Jolly")) { // IF DUE Jolly
					contJolly++;
					if (!dueJolly(cardCopy.get(i), cardCopy.get(i+1))) {
						if (cardCopy.get(i).getSeme().equals("Jolly") && !controllatoJolly) {
							controllatoJolly = true;
							cardCopy.get(i).setValore(cardCopy.get(i+1).getValore()-1);
						} else if (cardCopy.get(i+1).getSeme().equals("Jolly") && !controllatoJolly) {
							controllatoJolly = true;
							if (cardCopy.get(i).getValore()==13)
								cardCopy.get(i+1).setValore(1);
							else
								cardCopy.get(i+1).setValore(cardCopy.get(i).getValore()+1);
						}
					} else {
						contJolly++;
					}
					
				} else {
					return false;
				}
			}
			
			if (cardCopy.get(i).getValore()==13 && cardCopy.get(i+1).getValore()==1) {
				
			}			
			else if (cardCopy.get(i).getValore()+1!=cardCopy.get(i+1).getValore()) {
				return false;

			}
			
		}

		
		if (contJolly<limite) {
			for (int i=0; i<card.size(); i++) {
				if (card.get(i).getSeme().equals("Jolly") && cardCopy.get(i).getSeme().equals("Jolly")) {
					card.get(i).setValore(cardCopy.get(i).getValore());
				}

			}
			
			return true;
			
		} else {
			return false;
		}
		
		
	}
	

	
	
	
	public static boolean presenteJolly(ArrayList<Card> card) {
		int n = 0;
		for (Card c: card) {
			if (c.getValore()==14)
				n++;
		}
		
		if (n==1)
			return true;
		
		return false;
		
	}
	
	private static ArrayList<Card> combSenzaJolly (ArrayList<Card> card){
		ArrayList<Card> senzaJolly = new ArrayList<Card>(card);
		

		for (Card c: senzaJolly) {
			if (c.getValore()==14) {
				senzaJolly.remove(c);
				return senzaJolly;
			}
		}
		
		return senzaJolly;
		
	}
	
	public static int punti (ArrayList<Card> card) {
		int valore=0;
		for (int i=0; i<card.size(); i++) {
			valore += card.get(i).getValore();
		}
		return valore;
	}
	
	private static ArrayList<Card> copia(ArrayList<Card> card){
		ArrayList<Card> copia = new ArrayList<Card>();
		
		for (Card c: card) {
			copia.add(new Card(c.getSeme(), c.getValore(), c.getId()));
		}
		
		return copia;
		
	}
	
	private static boolean dueJolly (Card c1, Card c2) {
		
		return c1.getSeme().equals("Jolly") && c2.getSeme().equals("Jolly");
	}
	

}


