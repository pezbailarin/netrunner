package it.ck.cyberdeck.presentation;

import java.util.ArrayList;
import java.util.List;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.*;
import it.ck.cyberdeck.presentation.adapter.CardEntryListViewAdapter;
import it.ck.cyberdeck.presentation.adapter.DeckAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class DeckActivity extends Activity implements DeckPublisher{

	private TextView deckName;
	private TextView identityName;
	private ListView cardList;
	private List<CardEntry> deckEntries = new ArrayList<CardEntry>();
	private CardEntryListViewAdapter listViewAdapter;
	private DeckAdapter adapter;
	private Deck deck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);
		

		deckName = (TextView) findViewById(R.id.deck_name);
		identityName = (TextView) findViewById(R.id.deck_identity);
		if (savedInstanceState != null){
			deck = (Deck) savedInstanceState.getSerializable("deck");
		}	else {
			deck = (Deck) getIntent().getSerializableExtra("deck");
		}
		
		listViewAdapter = new CardEntryListViewAdapter(this.getApplicationContext(), deckEntries); 
		
		cardList = (ListView) findViewById(R.id.deck_cards);
		
		cardList.setAdapter(listViewAdapter);
		
		adapter = new DeckAdapter(deck);
		adapter.adapt(this);
		
		Button addButton = (Button) findViewById(R.id.add_card_button);
		
		addButton.setOnClickListener(new View.OnClickListener(){


			@Override
      public void onClick(View v) {
	      DeckService deckService = ((CyberDeckApp)getApplication()).getDeckService();
	      
	      CardLibrary cardLibrary = deckService.loadCardLibrary();
	      
	      List<Card> cardList2 = cardLibrary.getCardList();
	      Card selectedCard = null;
				for (Card card : cardList2){
	      	if(card.getIdentity().equals(Faction.HAAS_BIOROID)){
	      		selectedCard = card;
	      		break;
	      	}
	      }
	      
				deck.add(selectedCard);
				adapter.adapt(DeckActivity.this);
      }
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.deck, menu);
		return true;
	}

	@Override
	public void publishIdentityName(String identityName) {
		this.identityName.setText(identityName);
		
	}

	@Override
	public void publishDeckName(String deckName) {
		this.deckName.setText(deckName);
	}

	@Override
  public void publishCardList(List<CardEntry> cards) {
	  this.deckEntries= cards;
	  listViewAdapter.clear();
	  listViewAdapter.addAll(cards);
//	  listViewAdapter.notifyDataSetChanged();
  }

}