package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardDetailView;
import it.ck.cyberdeck.presentation.DownloaderView;
import it.ck.cyberdeck.presentation.service.ImageWorkerTask;
import it.ck.cyberdeck.presentation.service.ImageService;
import android.graphics.Bitmap;

public class CardDetailPresenter {

	private CardDetailView cardDetailFragment;
	private Card card;

	public CardDetailPresenter(CardDetailView cardDetailFragment) {
		this.cardDetailFragment = cardDetailFragment;
		setCard();
	}

	private void setCard() {
		card = cardDetailFragment.getCard();
	}

	public void populateView() {
		if (card != null) {
			String url = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"
					+ card.getKey().getCardCode() + ".png";
			ImageWorkerTask downloader = new ImageWorkerTask(url,
					(DownloaderView) cardDetailFragment, card.getKey());
			downloader.execute();
		}

	}

}
