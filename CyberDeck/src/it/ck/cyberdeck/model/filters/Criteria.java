package it.ck.cyberdeck.model.filters;

import it.ck.cyberdeck.model.Card;

import java.util.List;

public interface Criteria {

	List<Card> filter(List<Card> cards);

}