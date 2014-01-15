package it.ck.cyberdeck.model.filters;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import it.ck.cyberdeck.model.Card;

import java.util.List;

public class SubtypeCriteria implements Criteria {

	private String subtype;

	public SubtypeCriteria(String subtype) {
		this.subtype = subtype;
	}

	@Override
	public List<Card> filter(List<Card> cards) {
		return select(cards, having(on(Card.class).hasSubtype("\\b" + subtype + "\\b")));
	}

}
