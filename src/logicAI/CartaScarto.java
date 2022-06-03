package logicAI;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cartaScarto")
public class CartaScarto {
	
	@Param(0)
	private String seme;
	@Param(1)
	private int valore;
	@Param(2)
	private int id;
	
	
	public CartaScarto() {}
	
	public CartaScarto(String seme, int valore, int id) {
		this.seme = seme;
		this.valore = valore;
		this.id = id;
	}
	
	public String getSeme() {
		return seme;
	}
	public void setSeme(String seme) {
		this.seme = seme;
	}
	public int getValore() {
		return valore;
	}
	public void setValore(int valore) {
		this.valore = valore;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	

}
