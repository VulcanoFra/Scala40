package logicAI;

import java.util.ArrayList;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import model.Card;
import model.Mazzo;

@Id("quatris")
public class Quatris {
	

	@Param(0)
	private int id1;
	@Param(1)
	private int id2;
	@Param(2)
	private int id3;
	@Param(3)
	private int id4;
	
	public Quatris() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Quatris(int id1, int id2, int id3, int id4) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.id3 = id3;
		this.id4 = id4;
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



	public int getId4() {
		return id4;
	}

	public void setId4(int id4) {
		this.id4 = id4;
	}








	public ArrayList<Card> getQuatris() {
		
		ArrayList<Card> quatris = new ArrayList<Card>();
		
		
	
		quatris.add(Mazzo.getById(id1));
		quatris.add(Mazzo.getById(id2));
		quatris.add(Mazzo.getById(id3));
		quatris.add(Mazzo.getById(id4));
		
		return quatris;
		
	}


}