package application.controller;


import java.util.ArrayList;
import model.Card;
import model.Combinazione;
import model.Player;
import model.Utilities;
import view.Tavolo;

public class Turno {
	
	private Player umano;
	private Tavolo tavolo;
	
	public Turno(Player umano, Tavolo tavolo) {
		this.umano = umano;
		this.tavolo = tavolo;
	}
	
	public void turno() {

		umano.stampaCarteInMano();
		
		boolean scarto = false;
		
		boolean pescato = false;
		
		// GESTIRE PESCATA OBBLIGATORIA
				
		while (!scarto) {
			
			if(umano.haVinto()) {
				System.out.println();
				break;
			}

			Utilities.stampaInfo(" SELEZIONA OPERAZIONE DA FARE:");
			Utilities.stampaInfo(" - 0 PESCA DAL MAZZO ");
			Utilities.stampaInfo(" - 1 PESCA DAGLI SCARTI ");
			Utilities.stampaInfo(" - 2 SCARTA ");
			Utilities.stampaInfo(" - 3 SELEZIONA COMBINAZIONE DI CARTE ");
			Utilities.stampaInfo(" - 4 ATTACCA ");
			Utilities.stampaInfo(" - 5 MOSTRA CARTE IN MANO ");
			Utilities.stampaInfo(" - 6 MOSTRA TAVOLO ");
			System.out.println();
			int scelta = 8;
			String sceltaInput;
			
			try {
				sceltaInput= Utilities.scanner.next();
				scelta = Integer.parseInt(sceltaInput);
			} catch (Exception e) {
				System.out.println();
				Utilities.stampaGrassetto("Inserire un input valido!");
				System.out.println();
			}


			switch (scelta) {
			case 0: {
				System.out.println();
				if (!pescato || Utilities.debug) {
					Card cartaPescata = Utilities.game.getMazzo().estraiCarta();
					System.out.println("Hai pescato -> " + cartaPescata.toString() + " <-");
					System.out.println();
					umano.addCartaInMano(cartaPescata);
					umano.stampaCarteInMano();
					pescato = true;
				}
				else {
					Utilities.stampaInfoBox(" Hai già pescato! ");
					System.out.println();
				}
				
				break;
			}
			case 1: {
				System.out.println();
				if (Utilities.game.getScarti().getScarti().size()<1) {
					System.out.println();
					Utilities.stampaInfoBox(" Non ci sono scarti ");
					System.out.println();
				}
				else if (!pescato && umano.isCalato()) { 
					Card scartata = Utilities.game.getScarti().pescaScarto();
					umano.addCartaInMano(scartata);
					System.out.println("Hai pescato -> " + scartata.toString() + " <-");
					System.out.println();
					umano.stampaCompatta();
					System.out.println();
					pescato = true;
				} else if (!umano.isCalato()) {
					System.out.println();
					Utilities.stampaInfoBox(" Non puoi pescare prima di calarti dal mazzo degli scarti! ");
					System.out.println();
				} else if (pescato) {
					System.out.println();
					Utilities.stampaInfoBox(" Hai già pescato! ");
					System.out.println();
				}
				
				break;
			}
			case 2: {
				if(pescato) {
					System.out.println();
					umano.stampaCompatta();
					System.out.println();
					Utilities.stampaInfoBox(" Seleziona la carta da scartare (es. 5) ");
					System.out.println();
					int i;
					String scelta_i;
					
					try {
						scelta_i = Utilities.scanner.next();
						i = Integer.parseInt(scelta_i);
						Card card = umano.getCarteInMano().get(i-1);
						Utilities.game.getScarti().aggiungiScarto(card);
						umano.getCarteInMano().remove(card);
						System.out.println();
						System.out.println("CARTA SCARTATA -> " + card);
						scarto = true;
					} catch  (Exception e) {
						System.out.println();
						Utilities.stampaGrassetto("Seleziona una carta valida!");
						System.out.println();
					}
				} else if (umano.haVinto()){
					System.out.println();
					break;
				}
				else {
					System.out.println();
					Utilities.stampaGrassetto("NON HAI ANCORA PESCATO!");
					System.out.println();
				}
				
				break;
			}
			case 3: {
				if (pescato) {
					System.out.println();
					int comb = 0;
					int continuaComb=0;
					String comb_s;
					String continuaComb_s;
				
					
					ArrayList<Combinazione> arrayCombinazioni = new ArrayList<Combinazione>();
					
					
					while (continuaComb!=-1) {
						umano.stampaCompatta();
						System.out.println();
						Utilities.stampaInfoBox(" Seleziona le carte della combinazione terminato da -1 (es. 5 7 8 -1) ");
						ArrayList<Card> carteDaGiocare = new ArrayList<Card>();
						Combinazione combinazione = null;
	
						try {
							while (comb!=-1) {
									comb_s = Utilities.scanner.next();
									comb = Integer.parseInt(comb_s);
									if (comb > 0 && comb<=umano.getCarteInMano().size()) {
										carteDaGiocare.add(umano.getCarteInMano().get(comb-1));
									}else if (comb!=-1){
										carteDaGiocare.clear();
										System.out.println();
										Utilities.stampaGrassetto("Hai inserito un input sbagliato, reinserisci carte");
										System.out.println();
	
									}
							} // FINE WHILE piccolo
						
							
						if (carteDaGiocare.size()<3) {
							System.out.println();
							Utilities.stampaInfoBox(" HAI SELEZIONATO UNA COMBINAZIONE NON VALIDA! ");
							System.out.println();
						} else if (CheckCombinazioni.isQuatris(carteDaGiocare)) {
							System.out.println();
							System.out.println("_________________________________");
							System.out.println("HAI SELEZIONATO UN QUATRIS!");
							String stampa="";
							for (Card c: carteDaGiocare) {
								stampa += c.toString();
								stampa += " ";
							}
							System.out.println(stampa + "\n");
							combinazione = new Combinazione();
							combinazione.setTris(true);
							combinazione.setCombinazione(carteDaGiocare);
							arrayCombinazioni.add(combinazione);
							System.out.println("Totale punti combinazione: " + combinazione.getPunteggio());
							System.out.println("_________________________________");
							System.out.println();
		
						} else if (CheckCombinazioni.isTris(carteDaGiocare)) {
							System.out.println();
							System.out.println("_____________________________");
							System.out.println("HAI SELEZIONATO UN TRIS!");
							String stampa="";
								for (Card c: carteDaGiocare) {
									stampa += c.toString();
									stampa += " ";
								}
							System.out.println(stampa);
							combinazione = new Combinazione();
							combinazione.setTris(true);
							combinazione.setCombinazione(carteDaGiocare);
							arrayCombinazioni.add(combinazione);
							System.out.println("Totale punti combinazione: " + combinazione.getPunteggio());
							System.out.println("_____________________________");
							System.out.println();
		
						} else if (CheckCombinazioni.isScala(carteDaGiocare)) {
							System.out.println();
							System.out.println("_______________________________");
							System.out.println("HAI SELEZIONATO UNA SCALA!");
							String stampa="";
							for (Card c: carteDaGiocare) {
								stampa += c.toString();							
							}
							System.out.println(stampa + "\n");
							combinazione = new Combinazione();
							combinazione.setTris(false);
							combinazione.setCombinazione(carteDaGiocare);
							arrayCombinazioni.add(combinazione);
							System.out.println("Totale punti combinazione: " + combinazione.getPunteggio());
							System.out.println("_______________________________");
							System.out.println();
		
						} else  {
							System.out.println();
							Utilities.stampaInfoBox(" HAI SELEZIONATO UNA COMBINAZIONE NON VALIDA! ");
							System.out.println();
						}
						
						
						if (combinazione!=null) {
							for (Card c: carteDaGiocare) {
								umano.getCarteInMano().remove(c);
							}
							if (umano.isCalato()) {
								tavolo.addCombinazione(combinazione);
							}
							umano.stampaCarteInMano();
							System.out.println();
						}
							
						if (!umano.isCalato())
							Utilities.stampaInfoBox(" Se vuoi aggiungere combinazioni per calarti digita 0, altrimenti -1 ");
						else
							Utilities.stampaInfoBox(" Se vuoi aggiungere combinazioni digita 0, altrimenti -1 ");		
						
						System.out.println();
						continuaComb_s = Utilities.scanner.next();
						continuaComb = Integer.parseInt(continuaComb_s);
						comb = 0;
							
					} // fine try
					 catch (Exception e) {
						 Utilities.stampaGrassetto("Hai inserito un input non valido!");
					 }
				} // FINE WHILE grande
					
					// controllo punti
					if (!umano.isCalato()) {
						int punteggio = 0;
						for (Combinazione combPunti: arrayCombinazioni) {
							punteggio += combPunti.getPunteggio();
						}
						
						if (punteggio >= 40) {
							umano.playerCalato();
							for (Combinazione combinazione: arrayCombinazioni) {
								tavolo.addCombinazione(combinazione);
							 }	
							} else {
							for (Combinazione backup: arrayCombinazioni) {
								for (Card cartaBackup: backup.getCombinazione()) {
									umano.addCartaInMano(cartaBackup);
								}
							}
						}
						
					}
					System.out.println();
					umano.stampaCompatta();
					System.out.println();
					tavolo.stampaTavolo();
					System.out.println();
				} else {
					System.out.println();
					Utilities.stampaGrassetto("NON HAI ANCORA PESCATO!");
					System.out.println();
				}
				
				break;
				}
			case 4: {
				if (umano.isCalato() && pescato) {
					System.out.println();
					tavolo.stampaTavolo();	
					
					umano.stampaCompatta();
					
					int scegliComb;
					String scegliComb_s;
					int scegliCard;
					String scegliCard_s;
						
					try {
						Utilities.stampaInfoBox("  Scegli la combinazione su cui attaccare la tua carta (es. 1):  ");
						System.out.println();
						scegliComb_s = Utilities.scanner.next();
						scegliComb = Integer.parseInt(scegliComb_s);
						System.out.println();
						Utilities.stampaInfoBox("   Ora scegli quale carta attaccare (es. 2):   ");
						System.out.println();
						scegliCard_s = Utilities.scanner.next();
						System.out.println();
						scegliCard = Integer.parseInt(scegliCard_s);
						if (tavolo.attaccaCombinazione(umano.getCarteInMano().get(scegliCard-1), scegliComb)) {
							System.out.println();
							Utilities.stampaInfoBox(" Hai attaccato la tua carta! ");
							System.out.println();
							Card c = umano.getCarteInMano().get(scegliCard-1);
							umano.getCarteInMano().remove(c);
							tavolo.stampaTavolo();
						} else {
							System.out.println();
							Utilities.stampaInfoBox(" Non puoi attaccare la carta! ");
							System.out.println();
							
						}
					} catch (Exception e) {
						System.out.println();
						Utilities.stampaGrassetto("Hai inserito un inupt errato!");
						System.out.println();
					}
					
				} else if(!pescato){
					System.out.println();
					Utilities.stampaGrassetto("NON HAI ANCORA PESCATO!");
					System.out.println();
				} 
				
				else {
					System.out.println();
					Utilities.stampaInfoBox(" Non puoi attaccare prima di calarti! ");
					System.out.println();
				}
				break;
			}
			case 5: {
				System.out.println();
				umano.stampaCarteInMano();				
				break;
			}			
			case 6: {
				System.out.println();
				tavolo.stampaTavolo();	
				umano.stampaCompatta();
				System.out.println();
				break;
			}			
			
			default:
				System.out.println();
				Utilities.stampaGrassetto("SCELTA NON AMMESSA, RIPROVA");
				System.out.println();
				break;

			}
			
			
		}

		
		
	}

	
	
	
	
	

}

