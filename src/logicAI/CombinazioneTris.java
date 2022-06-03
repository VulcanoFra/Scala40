package logicAI;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("combinazioneTris")
public class CombinazioneTris {
	
	@Param(0)
	private String seme1;
	@Param(1)
	private String seme2;
	@Param(2)
	private String seme3;
	@Param(3)
	private int valore;
	@Param(4)
	private int id;
	
	public CombinazioneTris() {}
	
	public CombinazioneTris(String seme1, String seme2, String seme3, int valore, int id) {
		super();
		this.seme1 = seme1;
		this.seme2 = seme2;
		this.seme3 = seme3;
		this.valore = valore;
		this.id = id;
	}

	public String getSeme1() {
		return seme1;
	}

	public void setSeme1(String seme1) {
		this.seme1 = seme1;
	}

	public String getSeme2() {
		return seme2;
	}

	public void setSeme2(String seme2) {
		this.seme2 = seme2;
	}

	public String getSeme3() {
		return seme3;
	}

	public void setSeme3(String seme3) {
		this.seme3 = seme3;
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
	
	@Override
	public String toString() {
		String str = "Combinazion di tris con seme [" + seme1 + ", " + seme2 + ", " + seme3 + "] e valore ["+ valore +"] di ID (" + id + ")";
		return str;
	}
	
	
	

}
