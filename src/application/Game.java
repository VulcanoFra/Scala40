package application;

import application.controller.Turno;
import application.controller.TurnoAI;
import logicAI.DLVManager;
import model.Mazzo;
import model.MazzoScarti;
import model.Player;
import model.Utilities;
import view.Tavolo;

public class Game {
	
	private Mazzo mazzo = new Mazzo();
	private Player player1;
	private Player playerAI;
	private MazzoScarti scarti = new MazzoScarti();
	private Tavolo tavolo = new Tavolo();
	private DLVManager manager;
	
	public Game() {
	}
	
	public void startGame() {
		manager = new DLVManager();
		System.out.print("Inserisci il tuo nome: ");
		String nomePlayer =  Utilities.scanner.next();
		System.out.println();
		
		player1 = new Player();
		player1.setNome(nomePlayer);
		
		playerAI = new Player();
		playerAI.setNome("Giacomino");
		
		if(!Utilities.mazzoTruccato)
			mazzo.shuffle();
		daiCarte(player1);
		daiCarte(playerAI);
		
//		if (Utilities.debug)
//			playerAI.playerCalato();

		while (!player1.haVinto() && !playerAI.haVinto()) {
			
			Turno turnoA = new Turno(player1, tavolo);
			TurnoAI turnoB = new TurnoAI(playerAI, tavolo, manager);
			
			turnoA.turno();
			
			if (player1.haVinto()) {
				System.out.println();
				Utilities.stampaGrassetto(" --> " + player1.getNome().toUpperCase() + " <-- HA VINTO!");
				System.out.println();
			}
			else {
				System.out.println();
				turnoB.turnoPesca();
				turnoB.turno();
				if (playerAI.isCalato()) {
					turnoB.turnoAttacca();
					turnoB.turnoAttacca();
					turnoB.turnoAttacca();					
				}
				turnoB.turnoScarto();
			}
			
			System.out.println();
			
			tavolo.stampaTavolo();
			
			System.out.println();
			
			if (playerAI.haVinto())
				Utilities.stampaGrassetto(" --> " + playerAI.getNome().toUpperCase() + " <-- HA VINTO!");
			
			if (Utilities.debug) {
				turnoB.stampeDebug();
				System.out.println();
			}
			
		
		}
		
	}
	
	
	private void daiCarte(Player player) {
		for(int i = 0 ; i < 13 ;++i) {
			player.addCartaInMano(mazzo.estraiCarta());
		}
	}

	public Mazzo getMazzo() {
		return mazzo;
	}

	public void setMazzo(Mazzo mazzo) {
		this.mazzo = mazzo;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayerAI() {
		return playerAI;
	}

	public void setPlayerAI(Player playerAI) {
		this.playerAI = playerAI;
	}

	public MazzoScarti getScarti() {
		return scarti;
	}

	public void setScarti(MazzoScarti scarti) {
		this.scarti = scarti;
	}
	
	
	
	
}
