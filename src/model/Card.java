package model;


import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import utility.Colore;

@Id("card")
public class Card {
	
	@Param(0)
	private String seme;
	@Param(1)
	private int valore;
	@Param(2)
	private int id;
	
	public Card() {}
	
	public Card(String seme, int valore, int id) {
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
	
	
	@Override
	public String toString() {
		
		String figura = "";
		if (valore==10)
			figura = "10";
		else if (valore==11) {
			figura = "J";
		} else if (valore==12) {
			figura = "Q";
		} else if (valore==13) {
			figura = "K";
		}
	
		if (Utilities.coloreAttivo && !Utilities.modalitaLimitata) {
			
			if (valore==10) {
				if (seme.equalsIgnoreCase("F")) {
					return Colore.BLACK_BOLD + "["+ valore +" ♣]" + Colore.RESET;
				} else if (seme.equalsIgnoreCase("P")) {
					return Colore.BLACK_BOLD + "["+ valore +" ♠]" + Colore.RESET;
				} else if (seme.equalsIgnoreCase("C")) {
					return Colore.RED_BOLD_BRIGHT + "["+ valore +" ♥]" + Colore.RESET;
				} else if (seme.equalsIgnoreCase("Q")) {
					return Colore.RED_BOLD_BRIGHT + "["+ valore +" ♦]" + Colore.RESET;
				}
			}
		
			if (seme.equalsIgnoreCase("F") && valore<10)
				return Colore.BLACK_BOLD + "["+ valore +"  ♣]" + Colore.RESET;
			else if (seme.equalsIgnoreCase("F") && valore>10)
				return Colore.BLACK_BOLD + "["+ figura +"  ♣]" + Colore.RESET;
			else if (seme.equalsIgnoreCase("P") && valore<10)
				return Colore.BLACK_BOLD + "["+ valore +"  ♠]" + Colore.RESET;
			else if (seme.equalsIgnoreCase("P")  && valore>10)
				return Colore.BLACK_BOLD +"["+ figura +"  ♠]" + Colore.RESET;
			else if (seme.equalsIgnoreCase("C") && valore<10)
				return Colore.RED_BOLD_BRIGHT + "["+ valore +"  ♥]" + Colore.RESET;
			else if (seme.equalsIgnoreCase("C") && valore>10)
				return Colore.RED_BOLD_BRIGHT + "["+ figura +"  ♥]" + Colore.RESET;
			else if (seme.equalsIgnoreCase("Q") && valore<10)
				return Colore.RED_BOLD_BRIGHT + "["+ valore +"  ♦]" + Colore.RESET;
			else if (seme.equalsIgnoreCase("Q") && valore>10)
				return Colore.RED_BOLD_BRIGHT + "["+ figura +"  ♦]" + Colore.RESET;
	
				
				
			if (seme.equals("Jolly"))
				return Colore.PURPLE_BOLD + "[JOLLY]" + Colore.RESET;
			return Colore.PURPLE_BOLD + "[" + seme + "]" + Colore.RESET;
		
		} else if (!Utilities.modalitaLimitata){
			if (seme.equalsIgnoreCase("F") && valore<10)
				return "["+ valore +"  ♣]";
			else if (seme.equalsIgnoreCase("F") && valore>=10)
				return "["+ valore +" ♣]";
			else if (seme.equalsIgnoreCase("P") && valore<10)
				return "["+ valore +"  ♠]";
			else if (seme.equalsIgnoreCase("P")  && valore>=10)
				return "["+ valore +" ♠]";
			else if (seme.equalsIgnoreCase("C") && valore<10)
				return "["+ valore +"  ♥]";
			else if (seme.equalsIgnoreCase("C") && valore>=10)
				return "["+ valore +" ♥]";
			else if (seme.equalsIgnoreCase("Q") && valore<10)
				return "["+ valore +"  ♦]";
			else if (seme.equalsIgnoreCase("Q") && valore>=10)
				return "["+ valore +" ♦]";
	
			if (seme.equals("Jolly"))
				return "[JOLLY]";

			return "[" + seme + "]";
			
		} else {
			if (seme.equalsIgnoreCase("F") && valore<10)
				return "["+ valore +"  F]";
			else if (seme.equalsIgnoreCase("F") && valore>=10)
				return "["+ valore +" F]";
			else if (seme.equalsIgnoreCase("P") && valore<10)
				return "["+ valore +"  P]";
			else if (seme.equalsIgnoreCase("P")  && valore>=10)
				return "["+ valore +" P]";
			else if (seme.equalsIgnoreCase("C") && valore<10)
				return "["+ valore +"  C]";
			else if (seme.equalsIgnoreCase("C") && valore>=10)
				return "["+ valore +" C]";
			else if (seme.equalsIgnoreCase("Q") && valore<10)
				return "["+ valore +"  Q]";
			else if (seme.equalsIgnoreCase("Q") && valore>=10)
				return "["+ valore +" Q]";
	
			if (seme.equals("Jolly"))
				return "[JOLLY]";

			return "[" + seme + "]";
		}		
	}
	

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(this == obj)
			return true;
		
		if(obj.getClass() != this.getClass())
			return false;
		
		Card c = (Card) obj;
		
		return c.getId()==this.id;
	}
	
	
	
}
