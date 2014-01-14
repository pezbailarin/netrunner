package it.ck.cyberdeck.model.filters;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static org.hamcrest.Matchers.equalTo;
import it.ck.cyberdeck.model.Card;

import java.util.List;

public class NameCriteria implements Criteria {

	private String cardName;

	public NameCriteria(String cardName) {
		this.cardName = cardName;
	}

	@Override
	public List<Card> filter(List<Card> cards) {
		
		return select(cards, having(on(Card.class).getName(), equalTo(cardName)));
	}
}
