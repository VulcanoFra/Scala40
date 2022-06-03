package model;

import java.util.Stack;

public class MazzoScarti {
	
	private Stack<Card> scarti = new Stack<Card>();
	
	public MazzoScarti() {
	}

	public void aggiungiScarto(Card s) {
		scarti.add(s);
	}
	
	public Card pescaScarto() {
		return scarti.pop();
	}
	
	public Card getUltima() {
		return scarti.lastElement();
	}
	
	public Stack<Card> getScarti() {
		return scarti;
	}
	
	public void stampaUltima() {
		if (scarti.size()>0)
			System.out.print(scarti.lastElement().toString());
		else 
			System.out.print("[Vuoto]");
			
	}
	
}
