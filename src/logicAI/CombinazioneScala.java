package logicAI;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("combinazioneScala")
public class CombinazioneScala {

	@Param(0)
	private String seme;
	@Param(1)
	private int valore1;
	@Param(2)
	private int valore3;
	@Param(3)
	private int id;
	
	public CombinazioneScala() {}
	
	public CombinazioneScala(String seme, int valore1, int valore3, int id) {
		super();
		this.seme = seme;
		this.valore1 = valore1;
		this.valore3 = valore3;
		this.id = id;
	}

	public String getSeme() {
		return seme;
	}

	public void setSeme(String seme) {
		this.seme = seme;
	}

	public int getValore1() {
		return valore1;
	}

	public void setValore1(int valore1) {
		this.valore1 = valore1;
	}

	public int getValore3() {
		return valore3;
	}

	public void setValore3(int valore3) {
		this.valore3 = valore3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		String str = "Combinazione di scale parziali con seme [" + seme + "] "+ "da [ " + valore1 + "a valore " + valore3+  "] di ID (" + id + ")";
		return str;
	}
	
	
	

}
