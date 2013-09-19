package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.DeckStatus;
import it.ck.cyberdeck.model.Reason;
import it.ck.cyberdeck.model.StatusCode;
import it.ck.cyberdeck.presentation.BaseDeckActivity;
import it.ck.cyberdeck.presentation.DeckView;
import it.ck.cyberdeck.presentation.adapter.CardEntryListViewAdapter;
import it.ck.cyberdeck.presentation.fragment.CardDetailFragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DeckActivity extends BaseDeckActivity implements DeckView {

	private TextView identityName;
	private TextView deckStatusLine;
	private ListView cardList;
	private CardEntryListViewAdapter listViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);

		identityName = (TextView) findViewById(R.id.deck_identity);
		deckStatusLine = (TextView) findViewById(R.id.deckStatusLine);

		listViewAdapter = new CardEntryListViewAdapter(
				this.getApplicationContext());

		cardList = (ListView) findViewById(R.id.deck_cards);

		cardList.setAdapter(listViewAdapter);

		presenter.publish();
		cardList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CardEntry entry = listViewAdapter.getItem(position);
				Intent detailIntent = new Intent(DeckActivity.this,
						DeckDetailActivity.class);
				detailIntent.putExtra(BaseDeckActivity.DECK_ARG_ID,
						presenter.getDeck());
				detailIntent.putExtra(CardDetailFragment.ARG_ITEM_ID, entry);
				startActivity(detailIntent);

			}
		});

		registerForContextMenu(cardList);

		Button addButton = (Button) findViewById(R.id.add_card_button);

		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DeckActivity.this,
						AddCardActivity.class);
				intent.putExtra(DECK_ARG_ID, presenter.getDeck());
				startActivityForResult(intent, REQUEST_CODE);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.deck, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.deck_popoup_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.removeCard:
			removeCard(info.position);
			return true;
		case R.id.reoveAll:
			removeAll(info.position);
			return true;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void removeCard(int position) {
		CardEntry cardEntry = getEntry(position);
		presenter.remove(cardEntry.getCard());
		presenter.publish();
	}

	private void removeAll(int position) {
		CardEntry cardEntry = getEntry(position);
		presenter.removeAll(cardEntry.getCard());
		presenter.publish();
	}

	private CardEntry getEntry(int position) {
		return presenter.get(position);
	}

	@Override
	public void publishIdentityName(String identityName) {
		this.identityName.setText(identityName);
	}

	@Override
	public void publishDeckName(String deckName) {
		this.setTitle(deckName);
	}

	@Override
	public void publishEntryList(List<CardEntry> cards) {
		listViewAdapter.clear();
		listViewAdapter.addAll(cards);
	}

	@Override
	public void publishDeckStatus(DeckStatus deckStatus) {

		String statusLine = "";
		if (deckStatus.status().equals(StatusCode.INVALID)) {
			if (deckStatus.reason().equals(Reason.FEW_CARDS))
				statusLine = "There are not enough cards";
			if (deckStatus.reason().equals(Reason.FEW_AGENDA_POINTS))
				statusLine = "The agenda points are not valid for the deck size";
		}
		deckStatusLine.setText(statusLine);
	}

}