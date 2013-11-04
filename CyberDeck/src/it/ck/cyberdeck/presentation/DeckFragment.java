package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.model.Deck;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class DeckFragment extends Fragment{

	private Deck deck;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.deck = initDeck();
	}
	
	protected Deck getDeck(){
		return deck;
	}

	private Deck initDeck() {
		return ((BaseDeckActivity)getActivity()).getDeck();
	}
	
}
