package it.ck.cyberdeck.model.filters;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardSet;

import java.util.List;

public class CardSetCriteria implements Criteria {

	private CardSet cardSet;

	public CardSetCriteria(CardSet cardSet) {
		this.cardSet = cardSet;
	}

	@Override	
	public List<Card> filter(List<Card> cards) {
		return select(cards, having(on(Card.class).belongTo(cardSet)));
	}

}
