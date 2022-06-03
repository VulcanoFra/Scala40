package logicAI;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pesco")
public class Pesco {
	
	@Param(0)
	private int id;
	
	public Pesco() {}
	
	public Pesco(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
