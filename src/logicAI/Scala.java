package logicAI;

import java.util.ArrayList;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import model.Card;
import model.Mazzo;

@Id("scala")
public class Scala {
	
	@Param(0)
	private int id1;
	@Param(1)
	private int id2;
	@Param(2)
	private int id3;
	
	public Scala() {
		// TODO Auto-generated constructor stub
	}

	public Scala(int id1, int id2, int id3) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.id3 = id3;
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public int getId3() {
		return id3;
	}

	public void setId3(int id3) {
		this.id3 = id3;
	}
	
	public ArrayList<Card> getScala() {
		
		ArrayList<Card> scala = new ArrayList<Card>();
		scala.add(Mazzo.getById(id1));
		scala.add(Mazzo.getById(id2));
		scala.add(Mazzo.getById(id3));
		
		return scala;
		
	}

}
