package it.ck.cyberdeck.model.filters;

import it.ck.cyberdeck.model.Card;

import java.util.List;

public class OrCriteria implements Criteria {

	private Criteria left;
	private Criteria right;

	public OrCriteria(Criteria left, Criteria right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public List<Card> filter(List<Card> cards) {
		List<Card> filtered = left.filter(cards);
		List<Card> filteredRight = right.filter(cards);
		filtered.addAll(filteredRight);
		return filtered;
	}

}
