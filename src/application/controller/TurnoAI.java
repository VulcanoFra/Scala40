package application.controller;

import java.util.ArrayList;

import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import logicAI.Attacco;
import logicAI.CartaScarto;
import logicAI.CombinazioneScala;
import logicAI.CombinazioneTris;
import logicAI.DLVManager;
import logicAI.Pesco;
import logicAI.Quatris;
import logicAI.Scala;
import logicAI.Scarto;
import logicAI.Tris;
import model.Card;
import model.Combinazione;
import model.Mazzo;
import model.Player;
import model.Utilities;
import view.Tavolo;

public class TurnoAI {

	private Player playerAI;
	private Tavolo tavolo;
	private DLVManager dlvManager;
	
	private ArrayList<Object> listaDebug = new ArrayList<Object>();
	
	public TurnoAI(Player p, Tavolo t, DLVManager m) {
		playerAI = p;
		tavolo = t;	
		dlvManager = m;
	}
	
	public void turno() {
		if (Utilities.debug)
			playerAI.stampaCarteInMano();
		dlvManager.setProgramPlayer();

	
		
		if (Utilities.debug)
			playerAI.stampaCarteInMano();
		

		dlvManager.setInputPlayer(playerAI);
		
		AnswerSets answerSetComplessivi = dlvManager.answerSet();
		AnswerSet best;
		
		
		
		
		if(answerSetComplessivi==null) {
			Utilities.stampaDebug("AS NULL");
			
		} else if (answerSetComplessivi!=null) {
			
			
			try {
				best = answerSetComplessivi.getOptimalAnswerSets().get(0); 
			} catch (Exception e) {
				best = answerSetComplessivi.getAnswersets().get(0);
			}
				
			
				
	
			
			System.out.println();
			System.out.println();
			System.out.println();
			int punteggio=0;
			
			ArrayList<Combinazione> combinazioni = new ArrayList<Combinazione>();
			
			try {
				for (Object t: best.getAtoms()) {
					if (!(t instanceof Tris)) continue;
					listaDebug.add(t);
					Tris tris = (Tris) t;
					Utilities.stampaDebug("TRIS INIZIO");
					for (int i=0; i<tris.getTris().size(); i++) {
						Utilities.stampaDebug(tris.getTris().get(i).toString());
						if (tris.getTris().get(i).getSeme().equalsIgnoreCase("Jolly")) {
							tris.getTris().get(i).setValore(tris.getTris().get(i+1).getValore());
						}
					}
					Combinazione comb = new Combinazione();
					comb.setTris(true);
					comb.setCombinazione(tris.getTris());
					combinazioni.add(comb);
					punteggio+=comb.getPunteggio();
					Utilities.stampaDebug("Punteggio tris" + comb.getPunteggio());
					Utilities.stampaDebug("TRIS FINE");
				
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				for (Object q: best.getAtoms()) {
					if (!(q instanceof Quatris)) continue;
					listaDebug.add(q);
					Quatris quad = (Quatris) q;
					Utilities.stampaDebug("QUATRIS INIZIO");
					for (int i=0; i<quad.getQuatris().size(); i++) {
						Utilities.stampaDebug(quad.getQuatris().get(i).toString());
						if (quad.getQuatris().get(i).getSeme().equalsIgnoreCase("Jolly")) {
							quad.getQuatris().get(i).setValore(quad.getQuatris().get(i+1).getValore());
						}
					}
					Combinazione comb = new Combinazione();
					comb.setTris(true);
					comb.setCombinazione(quad.getQuatris());
					combinazioni.add(comb);
					punteggio+=comb.getPunteggio();
					Utilities.stampaDebug("Punteggio quad" + comb.getPunteggio());
					Utilities.stampaDebug("QUATRIS FINE");
				
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			try {
				for (Object s: best.getAtoms()) {
					if (!(s instanceof Scala)) continue;
					listaDebug.add(s);
					Scala scala = (Scala) s;
					Utilities.stampaDebug("SCALA INIZIO");
					for (int i=0; i<scala.getScala().size(); i++) {
						Utilities.stampaDebug(scala.getScala().get(i).toString());
						if (scala.getScala().get(i).getSeme().equalsIgnoreCase("Jolly")) {
							if (i==0) {
								if (scala.getScala().get(i+1).getValore()==1)
									scala.getScala().get(i).setValore(13);
								else
									scala.getScala().get(i).setValore(scala.getScala().get(i+1).getValore()-1);
							} else if (i==1) {
								if (scala.getScala().get(i+1).getValore()==1)
									scala.getScala().get(i).setValore(13);
								else if (scala.getScala().get(i+1).getValore()==2)
									scala.getScala().get(i).setValore(1);
								else
									scala.getScala().get(i).setValore(scala.getScala().get(i+1).getValore()-1);
								
							} else {
								if (scala.getScala().get(i-1).getValore()==13)
									scala.getScala().get(i).setValore(1);
								else
									scala.getScala().get(i).setValore(scala.getScala().get(i-1).getValore()+1);


								
							}
							
							
						}

					}
					Combinazione comb = new Combinazione();
					comb.setCombinazione(scala.getScala());
					comb.setTris(false);
					combinazioni.add(comb);
					punteggio+=comb.getPunteggio();
					Utilities.stampaDebug("Punteggio scala" + comb.getPunteggio());
					Utilities.stampaDebug("SCALA FINE");
				
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Utilities.stampaDebug("PUNTEGGIO FINALE:" + punteggio);
			if (punteggio>40 || playerAI.isCalato()) {
				for (Combinazione combi: combinazioni) {
					tavolo.addCombinazione(combi);
					for (Card c: combi.getCombinazione()) {
						playerAI.removeInMano(c);
					}
				}
				playerAI.playerCalato();
			} 

		
	} 
		
		
	}
	
	public void turnoScarto() {
		
		if (playerAI.getCarteInMano().size()<=0)
			return;
		
		if (Utilities.debug)
			playerAI.stampaCarteInMano();
		dlvManager.setProgramPlayerScarto();

		
		if (Utilities.debug) {
			System.out.println("Stampa per lo scarto");
			playerAI.stampaCarteInMano();
		}
		

		dlvManager.setInputPlayer(playerAI);
		
		AnswerSets answerSetComplessivi = dlvManager.answerSet();
		AnswerSet best;
		
		try {
			best = answerSetComplessivi.getOptimalAnswerSets().get(0); 
		} catch (Exception e) {
			best = answerSetComplessivi.getAnswersets().get(0);
		}
		
		
		try {
			for (Object s: best.getAtoms()) {
				if (!(s instanceof Scarto)) continue;
				listaDebug.add(s);
				Scarto scarto = (Scarto) s;
				Card cartaScartata = scarto.getScarto();
				Utilities.stampaDebug("Scarto -> " + cartaScartata);
				playerAI.removeInMano(cartaScartata);
				Utilities.game.getScarti().aggiungiScarto(cartaScartata);
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (Utilities.debug) {
			playerAI.stampaCompatta();
		}
		
		
		
	} // fine scarto
	
	public void turnoPesca() {
		
		if (!playerAI.isCalato()) {
			Utilities.stampaDebug("NON POSSO PESCARE ANCORA DAGLI SCARTI");
			playerAI.addCartaInMano(Utilities.game.getMazzo().estraiCarta());
			return;
		}


		
		if (Utilities.debug)
			playerAI.stampaCarteInMano();
		
		Utilities.stampaDebug("SEZIONE PESCA");
		
		dlvManager.setProgramPlayerScarto();
		
		Card tmp = Utilities.game.getScarti().getUltima();
		CartaScarto daPescare = new CartaScarto(tmp.getSeme(), tmp.getValore(), tmp.getId());
		dlvManager.setInputPlayerPesca(playerAI, daPescare);
		
		boolean pescare = false;
		
		AnswerSets answerSetComplessivi = dlvManager.answerSet();
		AnswerSet best;
		
		try {
			best = answerSetComplessivi.getOptimalAnswerSets().get(0); 
		} catch (Exception e) {
			best = answerSetComplessivi.getAnswersets().get(0);
		}
		
		
		try {
			for (Object s: best.getAtoms()) {
				if (!(s instanceof Pesco)) continue;
				listaDebug.add(s);
				pescare = true;
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (Utilities.pescaScarti)
			pescare = true;
		
		if (pescare) {
			Utilities.stampaDebug("PESCO DAGLI SCARTI -> " + Utilities.game.getScarti().getUltima());
			playerAI.addCartaInMano(Utilities.game.getScarti().pescaScarto());
		} else {
			Utilities.stampaDebug("PESCO DAL MAZZO ");
			playerAI.addCartaInMano(Utilities.game.getMazzo().estraiCarta());
		}
		
		Utilities.stampaDebug("FINE SEZIONE PESCA");

		
	} // fine pesca
	
	public void turnoAttacca() {
		
		Utilities.stampaDebug("ZONA ATTACCO");
		dlvManager.setProgramPlayerAttacco();
		ArrayList<CombinazioneTris> tris = new ArrayList<CombinazioneTris>();
		ArrayList<CombinazioneScala> scale = new ArrayList<CombinazioneScala>();
		
		for (int i=0; i<tavolo.getCombinazioni().size(); i++) {
			if (tavolo.getCombinazioni().get(i).isTris() && tavolo.getCombinazioni().get(i).getCombinazione().size()==3) {
				tris.add(tavolo.getCombinazioneTris(i));
				Utilities.stampaDebug("Trovata combinazione tris --> " + tavolo.getCombinazioneTris(i).toString());
			} else if(!tavolo.getCombinazioni().get(i).isTris()) {
				scale.add(tavolo.getCombinazioneScala(i));
				Utilities.stampaDebug("Trovata combinazione scala --> " + tavolo.getCombinazioneScala(i).toString());				
			}
		}
		
		dlvManager.setInputPlayerAttacco(playerAI, tris, scale);
		
		
		AnswerSets answerSetComplessivi = dlvManager.answerSet();
		AnswerSet best;
		
		try {
			best = answerSetComplessivi.getOptimalAnswerSets().get(0); 
		} catch (Exception e) {
			best = answerSetComplessivi.getAnswersets().get(0);
		}
		
		
		
		try {
			for (Object a: best.getAtoms()) {
				if (!(a instanceof Attacco)) continue;
				listaDebug.add(a);
				Attacco attacco = (Attacco) a;
				Utilities.stampaDebug("ATTACCO " + Mazzo.getById(attacco.getId_carta()).toString() + " NELLA COMBINAZIONE -> " + attacco.getId_combinazione());
				if (!tavolo.getCombinazioni().get(attacco.getId_combinazione()).isTris()) {
//					if(!Mazzo.getById(attacco.getId_carta()).getSeme().equalsIgnoreCase("Jolly")) {
						if (tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().get(0).getValore()> Mazzo.getById(attacco.getId_carta()).getValore()) {
							tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().add(0, Mazzo.getById(attacco.getId_carta()));
							playerAI.getCarteInMano().remove(Mazzo.getById(attacco.getId_carta()));
						} else {
							tavolo.getCombinazioni().get(attacco.getId_combinazione()).addCartaCombinazione(Mazzo.getById(attacco.getId_carta()));
							playerAI.getCarteInMano().remove(Mazzo.getById(attacco.getId_carta()));	
						}
					} else {
						tavolo.getCombinazioni().get(attacco.getId_combinazione()).addCartaCombinazione(Mazzo.getById(attacco.getId_carta()));

					}
//				} else {
//					playerAI.getById(attacco.getId_carta()).setValore(tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().get(tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().size()-1).getValore()+1);
//					tavolo.getCombinazioni().get(attacco.getId_combinazione()).addCartaCombinazione(Mazzo.getById(attacco.getId_carta()));
////					tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().get(tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().size()-1).setValore(Mazzo.getById(attacco.getId_carta()).getValore()+1);
//					Utilities.stampaDebug("Attacco un jolly alla combinazione --> " + tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().get(tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().size()-1).toString() + " con valore " + tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().get(tavolo.getCombinazioni().get(attacco.getId_combinazione()).getCombinazione().size()-1).getValore());
//					playerAI.getCarteInMano().remove(Mazzo.getById(attacco.getId_carta()));
//				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void stampeDebug() {
		try {
			for (Object s: listaDebug) {
				Utilities.stampaDebug(s.toString());

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}


