package it.ck.cyberdeck.presentation.fragment;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.presentation.DeckFragment;
import it.ck.cyberdeck.presentation.activity.CardGalleryActivity;
import it.ck.cyberdeck.presentation.adapter.CardEntryGridAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class CardEntryFragment extends DeckFragment {

	private CardEntryGridAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new CardEntryGridAdapter(getActivity(), getDeck());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_card_grid, container);
		GridView gridview = (GridView)rootView.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		OnItemClickListener listener = new OnItemClickListener(){
			  @Override
			    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				  Intent detailIntent = new Intent(CardEntryFragment.this.getActivity(), CardGalleryActivity.class);
					detailIntent.putExtra(CardDetailFragment.ARG_ITEM_ID, adapter.getItem(position));
					startActivity(detailIntent);
			    }
		};
		gridview.setOnItemClickListener(listener );
		return rootView;
	}
	
	
	
	
}
