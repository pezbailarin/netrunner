package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.model.CardEntry;

import java.util.List;

public interface DeckPublisher {
	
	void publishIdentityName(String identityName);
	void publishDeckName(String deckName);
	void publishCardList(List<CardEntry> cards);
	
}