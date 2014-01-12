package it.ck.cyberdeck.model.filters;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;

import java.util.List;

public class OrCriteria implements Criteria {

	private Criteria left;
	private Criteria right;

	public OrCriteria(Criteria left, Criteria right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public List<Card> filter(CardLibrary library) {
		List<Card> filtered = left.filter(library);
		List<Card> filteredRight = right.filter(library);
		filtered.addAll(filteredRight);
		return filtered;
	}

}
