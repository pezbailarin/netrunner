package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.R.layout;
import it.ck.cyberdeck.R.menu;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.adapter.DeckGridAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class DeckGridViewActivity extends Activity {

	private Deck deck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_grid_view);
		
		GridView gv = (GridView) findViewById(R.id.gridView1);
		if (savedInstanceState != null){
			deck = (Deck) savedInstanceState.getSerializable("deck");
		}	else {
			deck = (Deck) getIntent().getSerializableExtra("deck");
		}
		gv.setAdapter(new DeckGridAdapter(this, deck));
		
		 gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Toast.makeText(DeckGridViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
				
			}
		        
		    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deck_grid_view, menu);
		return true;
	}
	
	@Override
    protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("deck", deck);
		super.onSaveInstanceState(outState);
    }
}
