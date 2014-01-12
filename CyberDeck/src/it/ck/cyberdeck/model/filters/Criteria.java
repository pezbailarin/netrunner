package it.ck.cyberdeck.model.filters;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;

import java.util.List;

public interface Criteria {

	List<Card> filter(CardLibrary library);

}