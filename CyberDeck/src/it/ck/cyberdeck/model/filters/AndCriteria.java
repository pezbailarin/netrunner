package it.ck.cyberdeck.model.filters;

import static ch.lambdaj.Lambda.select;
import static org.hamcrest.Matchers.isIn;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;

import java.util.List;

public class AndCriteria implements Criteria {

	private Criteria left;
	private Criteria right;

	public AndCriteria(Criteria left, Criteria right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public List<Card> filter(CardLibrary library) {
		List<Card> filtered = left.filter(library);
		List<Card> filteredRight = right.filter(library);
		return select(filtered, isIn(filteredRight));
	}

}
