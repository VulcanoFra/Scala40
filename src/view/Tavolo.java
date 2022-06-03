package view;

import java.util.ArrayList;

import application.controller.CheckCombinazioni;
import logicAI.CombinazioneScala;
import logicAI.CombinazioneTris;
import model.Card;
import model.Combinazione;
import model.Utilities;
import utility.Colore;

public class Tavolo {
	
	private ArrayList<Combinazione> combinazioni;
	
	public Tavolo() {
		combinazioni = new ArrayList<Combinazione>();
	}
	
	public void addCombinazione(Combinazione comb) {
		combinazioni.add(comb);
	}	
	
	
	public ArrayList<Combinazione> getCombinazioni (){
		return combinazioni;
	}
	
	// prima di generarale dal turnoAI fare il check che una combinazione sia tris con 3 elementi
	public CombinazioneTris getCombinazioneTris(int index) {
		Card c1 = new Card(combinazioni.get(index).getCombinazione().get(0).getSeme(), combinazioni.get(index).getCombinazione().get(0).getValore(), combinazioni.get(index).getCombinazione().get(0).getId());
		Card c2 = new Card(combinazioni.get(index).getCombinazione().get(1).getSeme(), combinazioni.get(index).getCombinazione().get(1).getValore(), combinazioni.get(index).getCombinazione().get(1).getId());
		Card c3 = new Card(combinazioni.get(index).getCombinazione().get(2).getSeme(), combinazioni.get(index).getCombinazione().get(2).getValore(), combinazioni.get(index).getCombinazione().get(2).getId());
		CombinazioneTris comb = new CombinazioneTris(c1.getSeme(),c2.getSeme(),c3.getSeme(), c1.getValore(), index);
		return comb;
	}
	// prima di generarale dal turnoAI fare il check che una combinazione sia scala con 3 elementi
//	public CombinazioneScala getCombinazioneScala(int index) {
//		Card c1 = new Card(combinazioni.get(index).getCombinazione().get(0).getSeme(), combinazioni.get(index).getCombinazione().get(0).getValore(), combinazioni.get(index).getCombinazione().get(0).getId());
//		Card c2 = new Card(combinazioni.get(index).getCombinazione().get(1).getSeme(), combinazioni.get(index).getCombinazione().get(1).getValore(), combinazioni.get(index).getCombinazione().get(1).getId());
//		Card c3 = new Card(combinazioni.get(index).getCombinazione().get(2).getSeme(), combinazioni.get(index).getCombinazione().get(2).getValore(), combinazioni.get(index).getCombinazione().get(2).getId());
//		CombinazioneScala comb = new CombinazioneScala(c1.getSeme(), c1.getValore(),c2.getValore(), c3.getValore(), index);
//		return comb;
//	}
	public CombinazioneScala getCombinazioneScala(int index) {
		int size = combinazioni.get(index).getCombinazione().size()-1;
		Card c1 = new Card(combinazioni.get(index).getCombinazione().get(0).getSeme(), combinazioni.get(index).getCombinazione().get(0).getValore(), combinazioni.get(index).getCombinazione().get(0).getId());
		Card c2 = new Card(combinazioni.get(index).getCombinazione().get(size).getSeme(), combinazioni.get(index).getCombinazione().get(size).getValore(), combinazioni.get(index).getCombinazione().get(size).getId());
		CombinazioneScala comb = new CombinazioneScala(c1.getSeme(), c1.getValore(), c2.getValore(), index);
		return comb;
	}
	
	public boolean attaccaCombinazione(Card carta, int index) {
		
		if (index>combinazioni.size() || index<1) {
			System.out.println("La combinazione non esiste!");
			return false;
			}
		
		
		Combinazione combinazione = combinazioni.get(index-1);
		int valore = carta.getValore();
		String seme = carta.getSeme();
		
		
		if (combinazione.haJolly() && seme.equals("Jolly")) {
			return false;
		}
		
		// TRIS
		
		if (CheckCombinazioni.isTris(combinazione.getCombinazione())) {
			
			if (seme.equals("Jolly")){

			} else {
			
				for (Card c: combinazione.getCombinazione()) {
					 if (!c.getSeme().equals("Jolly")) {
						if (c.getValore()!=valore)
							return false;
						else if (c.getSeme().equals(seme))
							return false;
						
					}
				}
			} // else if
			
			combinazione.addCartaCombinazione(carta);
			return true;
			
		} // FINE TRIS
		else if (CheckCombinazioni.isScala(combinazione.getCombinazione())) {
			
			String semeScala;
			if (combinazione.getCombinazione().get(0).getSeme().equals("Jolly"))
				semeScala = combinazione.getCombinazione().get(1).getSeme();
			else
				semeScala = combinazione.getCombinazione().get(0).getSeme();
			
		// caso Jolly
				if (seme.equals("Jolly")) {
					Utilities.stampaGrassetto("Vuoi aggiungere a destra o sinistra? D/S");
					String scelta = Utilities.scanner.next();
					if (scelta.equalsIgnoreCase("s")) {
						if(combinazione.getCombinazione().get(0).getValore()-1 == 0)
							return false;
						carta.setValore(combinazione.getCombinazione().get(0).getValore()-1);
						combinazione.getCombinazione().add(0, carta);
						return true;
					} else if (scelta.equalsIgnoreCase("d")) {
						int v = combinazione.getCombinazione().get(combinazione.getCombinazione().size()-1).getValore()+1;
						if(v == 14)
							v = 1;
						carta.setValore(v);
						combinazione.getCombinazione().add(carta);
						return true;
					} else {
						Utilities.stampaInfoBox(" Non hai selezionato una scelta valida! ");
						return false;
					}
				}
					
		// caso Jolly
			

				
			if (valore == combinazione.getCombinazione().get(0).getValore()-1 && seme.equals(semeScala)) {
				combinazione.getCombinazione().add(0, carta);
				return true;
			} else if (valore == combinazione.getCombinazione().get(combinazione.getCombinazione().size()-1).getValore()+1  && seme.equals(semeScala)) {
				combinazione.getCombinazione().add(carta);
				return true;
			}
			
		}
		
		
		
		return false;
	}
	
	public void stampaTavolo() {
		
		if (Utilities.coloreAttivo) {
		
			
			System.out.println(Colore.FOREST_GREEN +  "======================= " + Colore.RESET + Colore.BLACK_BOLD + "TAVOLO" + Colore.RESET  + Colore.FOREST_GREEN +  " ========================" + Colore.RESET);
			System.out.println(Colore.FOREST_GREEN + "  ---------                 ----------" + Colore.RESET);
			System.out.print(Colore.FOREST_GREEN + " | " + Colore.RESET);
			Utilities.game.getScarti().stampaUltima();
			System.out.print(Colore.FOREST_GREEN + "  |" + Colore.RESET + Colore.BLACK_ITALIC + " <-- SCARTO   " + Colore.RESET);
			
			System.out.print(Colore.FOREST_GREEN + " | " + Colore.RESET);
			System.out.print(Colore.BLUE_BOLD_BRIGHT+ " [XXXX]" + Colore.RESET);
			System.out.print(Colore.FOREST_GREEN + "  |" + Colore.RESET + Colore.BLACK_ITALIC + " <-- MAZZO\n" + Colore.RESET);

			
			System.out.println(Colore.FOREST_GREEN + "  ---------                 ----------" + Colore.RESET);
			System.out.println();
			
			
			for (int i=0; i<combinazioni.size(); i++) {
				
				Combinazione combinazione = combinazioni.get(i);
				String stampa = Colore.BLACK_ITALIC + " " + (i+1) + "):" + Colore.RESET + " ";
				
				for (Card c: combinazione.getCombinazione()) {
					stampa += c.toString() + " ";
				}
				
				System.out.println(stampa);
				
			}
			
			System.out.println();
			System.out.println(Colore.FOREST_GREEN + " ======================================================" + Colore.RESET);
			System.out.println();
	
	
			
		} else {
			
			System.out.println(" ======================================================= TAVOLO =======================================================");
			System.out.println(" ----------");
			System.out.print(" | ");
			Utilities.game.getScarti().stampaUltima();
			System.out.print("  | <-- SCARTO\n");
			System.out.println(" ----------");
			System.out.println();
			
			for (int i=0; i<combinazioni.size(); i++) {
				
				Combinazione combinazione = combinazioni.get(i);
				String stampa = " " + (i+1) + "): ";
				
				for (Card c: combinazione.getCombinazione()) {
					stampa += c.toString() + " ";
				}
				
				System.out.println(stampa);
				
			}
			
			System.out.println();
			System.out.println(" ======================================================================================================================");
			System.out.println();

			
			
		}
	
	}
	
	

	

}
