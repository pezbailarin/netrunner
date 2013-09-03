package it.ck.cyberdeck.model;

public class CardTestFactory{
	public static Card getAnarchCard() {
		Faction faction = Faction.ANARCH;
		return getCard(faction);
	}
	public static Card getShaperCard() {
		Faction shaper = Faction.SHAPER;
		return getCard(shaper);
	}
	
	public static Card getCard(Faction faction) {
		return new Card("Name", Side.RUNNER, faction, 5, null);
	}
	
}