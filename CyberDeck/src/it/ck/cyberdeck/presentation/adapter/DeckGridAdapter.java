package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.DeckGridViewActivity;
import it.ck.cyberdeck.presentation.service.AndroidFSImageService;
import it.ck.cyberdeck.presentation.service.ImageService;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class DeckGridAdapter extends BaseAdapter implements ListAdapter {

	private Context context;
	private Deck deck;
	
	public DeckGridAdapter(DeckGridViewActivity deckGridViewActivity, Deck deck) {
		context = deckGridViewActivity;
		this.deck = deck;
	}

	@Override
	public int getCount() {
		return deck.cards().size();
	}

	@Override
	public Object getItem(int position) {
		
		return deck.cards().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(context);
	            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	            imageView.setPadding(15, 15, 15, 15);
	            
	        } else {
	            imageView = (ImageView) convertView;
	        }
	        ImageService is = new AndroidFSImageService(context);
	        imageView.setImageBitmap(is.getCardImage(((CardEntry)getItem(position)).getCard().getKey()));
	        return imageView;
	}

}
