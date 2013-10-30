package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class CachedImageService implements ImageService{

	private LruCache<String, Bitmap> cache;
	private ImageService delegate;

	public CachedImageService (ImageService delegate, LruCache<String, Bitmap> cache){
		this.delegate = delegate;
		this.cache = cache;
		
	}
	
	@Override
	public Bitmap getCardImage(CardKey key) {
		Bitmap bmp = cache.get(key.getCardCode());
		if (bmp == null){
			bmp = delegate.getCardImage(key);
			cache.put(key.getCardCode(), bmp);
		}
		return bmp;
	}

	@Override
	public Bitmap getCardThumbnail(CardKey key) {
		Bitmap bmp = cache.get(key.getCardCode()+ "_tn");
		if (bmp == null){
			bmp = delegate.getCardThumbnail(key);
			cache.put(key.getCardCode()+ "_tn", bmp);
		}
		return bmp;
	}

	@Override
	public Bitmap getCardImage(CardKey key, int tmbPixWidth, int tmbPixHeight) {
		Bitmap bmp = cache.get(key.getCardCode());
		if (bmp == null){
			bmp = delegate.getCardImage(key, tmbPixWidth, tmbPixHeight);
			cache.put(key.getCardCode(), bmp);
		}
		return bmp;
	}

	@Override
	public Bitmap getCardThumbnail(CardKey key, int tmbPixWidth,
			int tmbPixHeight) {
		Bitmap bmp = cache.get(key.getCardCode()+ "_tn");
		if (bmp == null){
			bmp = delegate.getCardThumbnail(key, tmbPixWidth, tmbPixHeight);
			cache.put(key.getCardCode()+ "_tn", bmp);
		}
		return bmp;
	}

}
