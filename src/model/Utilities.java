package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import application.Game;
import utility.Colore;

public class Utilities {
	public static final boolean debug = false;
	public static final boolean mazzoTruccato = false;
	public static final boolean pescaScarti = false;
	public static final boolean impostazioniDefault = false;
	public static boolean modalitaLimitata = false;
	public static int os = 2;
	public static final ArrayList<String> SEMI = new ArrayList<String>(Arrays.asList("Q", "C", "F", "P"));
	public static Scanner scanner = new Scanner(System.in);
	
	public static boolean coloreAttivo = true;
	
	public static Game game = new Game();
	
	public static void carteOrdinatePerValore(ArrayList<Card> card){
		
		for (int i=0; i<card.size(); i++) {
			for (int j=i+1; j<card.size(); j++) {
				if (card.get(i).getValore()>card.get(j).getValore()) {
					Card tmp = card.get(i);
					card.set(i, card.get(j));
					card.set(j, tmp);
				}
			}
		}
		
	}
	
	public static void stampaGrassetto(String str) {
		
		if (coloreAttivo) {
			System.out.println(Colore.BLACK_BOLD + str + Colore.RESET);
		} 
		else
			System.out.println(str);
		
		
	}	
	
	public static void stampaInfo(String str) {
		
		if (coloreAttivo) {
			System.out.println(Colore.BLUE_BOLD + str + Colore.RESET);
		} 
		else
			System.out.println(str);
		
	}
	

	public static void stampaInfoBox(String str) {
		
		if (coloreAttivo) {
			System.out.println(Colore.BOXING + str + Colore.RESET);
		} 
		else
			System.out.println(str);
	}
	


	public static boolean inputValido(int scelta, int min, int max) {
		if (scelta>= min && scelta<=max) {
			return true;
		} else {
			if (coloreAttivo) {
				System.out.println();
				System.out.println(Colore.BLACK_BOLD + "Hai selezionato " + scelta + " e non è valida!" + Colore.RESET);
				System.out.println(Colore.BLACK_BOLD + "RIPETI L'INSERIMENTO" + Colore.RESET);
				System.out.println();
			}
			else {
				System.out.println();
				System.out.println("Hai selezionato " + scelta + " e non è valida!");
				System.out.println("RIPETI L'INSERIMENTO");
				System.out.println();

			}
			
			return false;
			
		}
	}
	
	public static void stampaDebug (String str) {
		if (debug)
			stampaGrassetto(str);
	}
		
	
}
