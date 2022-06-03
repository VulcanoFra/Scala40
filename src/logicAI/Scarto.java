package logicAI;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import model.Card;
import model.Mazzo;

@Id("scarto")
public class Scarto {
	
	@Param(0)
	private int id;
	
	public Scarto() {
		// TODO Auto-generated constructor stub
	}
	
	public Scarto(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Card getScarto() {
		return Mazzo.getById(id);
	}
	

}
