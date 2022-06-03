package logicAI;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("attacco")
public class Attacco {
	
	@Param(0)
	private int id_combinazione;
	@Param(1)
	private int id_carta;
	
	public Attacco() {}
	
	
	
	public Attacco(int id_combinazione, int id_carta) {
		super();
		this.id_combinazione = id_combinazione;
		this.id_carta = id_carta;
	}




	public int getId_combinazione() {
		return id_combinazione;
	}
	public void setId_combinazione(int id_combinazione) {
		this.id_combinazione = id_combinazione;
	}
	public int getId_carta() {
		return id_carta;
	}
	public void setId_carta(int id_carta) {
		this.id_carta = id_carta;
	}
	
	

}
