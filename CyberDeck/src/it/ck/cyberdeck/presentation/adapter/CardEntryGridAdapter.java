package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.CyberDeckApp;
import it.ck.cyberdeck.presentation.service.ImageTask;
import it.ck.cyberdeck.presentation.service.ThumbnailImageTask;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CardEntryGridAdapter extends BaseAdapter {

	private Context context;
	private List<CardEntry> entries;
	private int tmbPixHeight;
	private int tmbPixWidth;

	public CardEntryGridAdapter(Context c, List<CardEntry> entries) {
		this.context = c;
		this.entries = entries;
		this.tmbPixHeight = c.getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_size);
		this.tmbPixWidth = c.getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_size);
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
	public View getView(int position, View convertView, ViewGroup parent) {
    	ImageDowloaderView imageView;
        if (convertView == null) { 
            imageView = new ImageDowloaderView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(tmbPixWidth, tmbPixHeight));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setBackgroundResource(R.drawable.runner_back);
        } else {
            imageView = (ImageDowloaderView) convertView;
            imageView.setImage(null);
            
        }
        
        imageView.setBackgroundResource(R.drawable.runner_back);
        CardKey key = getItem(position).getKey();
		
        ImageTask task = new ThumbnailImageTask(imageView, key,getCyberDeckApp().getCachedImageService(), tmbPixWidth, tmbPixHeight);
		imageView.setTask(task);
        task.execute();
        
        return imageView;
	}

}
