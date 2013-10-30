package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.presentation.CyberDeckApp;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CardEntryGridAdapter extends BaseAdapter {

	private Context context;
	private List<CardEntry> entries;
	private int tmbPixHeight;
	private int tmbPixWidth;
	private Object imageCache;

	public CardEntryGridAdapter(Context c, List<CardEntry> entries) {
		this.context = c;
		this.entries = entries;
		this.tmbPixHeight = c.getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_size);
		this.tmbPixWidth = c.getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_size);
		this.imageCache = getCyberDeckApp().getImageCache();
	}

	private CyberDeckApp getCyberDeckApp() {
		return (CyberDeckApp) context.getApplicationContext();
	}

	@Override
	public int getCount() {
		return entries.size();
	}

	@Override
	public CardEntry getItem(int position) {
		return entries.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
