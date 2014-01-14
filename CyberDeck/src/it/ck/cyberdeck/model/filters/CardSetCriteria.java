package it.ck.cyberdeck.model.filters;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardSet;

import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static ch.lambdaj.Lambda.*;


public class CardSetCriteria implements Criteria {

	private CardSet cardSet;

	public CardSetCriteria(CardSet cardSet) {
		this.cardSet = cardSet;
	}

	@Override	
	public List<Card> filter(List<Card> cards) {
		return select(cards, having(on(Card.class).getCardSet(), equalTo(cardSet)));
	}

}
