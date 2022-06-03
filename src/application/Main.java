package application;


import model.Utilities;

public class Main {
	public static void main(String[] args) {
		
		
		if (!Utilities.impostazioniDefault) {
			System.out.println("Se per problemi di compatibilità preferisci giocare in modalità limitata (senza caratteri ascii dei semi e senza colori) digita 'L', altrimenti 'N'");
			System.out.println();
			String scelta = Utilities.scanner.next();
			if (scelta.equalsIgnoreCase("L")) {
				Utilities.modalitaLimitata = true;
				Utilities.coloreAttivo = false;
			}
	
			System.out.println();
			
			if (!Utilities.modalitaLimitata) {
				System.out.print("SE VUOI GIOCARE IN MODALITA' A COLORI PREMI 'C', ALTRIMENTI 'N' : ");
				scelta = Utilities.scanner.next();
				if (scelta.equalsIgnoreCase("C"))
					Utilities.coloreAttivo = true;
				else
					Utilities.coloreAttivo = false;
			
			}
			
			System.out.println();
			Utilities.stampaGrassetto("INDICA IL TUO SISTEMA OPERATIVO DIGITANDONE LA PRIMA LETTERA --> Windows = 'W', Linux = 'L', Mac = 'M' <--");
			System.out.println();
			String os_scelto = Utilities.scanner.next();
			if (os_scelto.equalsIgnoreCase("W")) {
				Utilities.os = 0;
			}
			 else if (os_scelto.equalsIgnoreCase("L")) {
				Utilities.os = 1;	
			}
			 else if (os_scelto.equalsIgnoreCase("M")) {
				Utilities.os = 2;
			}
			
			System.out.println();
		}
		
		


		Utilities.game.startGame();
		
		
	}
}
