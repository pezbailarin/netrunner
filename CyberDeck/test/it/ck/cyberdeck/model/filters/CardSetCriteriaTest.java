package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardSet;

import java.util.List;

import org.junit.Test;

public class CardSetCriteriaTest extends CriteriaTest {

	@Test
	public void givenACardLibrary_IcanFilterByCardSet() {
		Criteria filter = new CardSetCriteria(CardSet.CORE);
		
		List<Card> filtered = filter.filter(getSampleCardList());
		
		assertThat(filtered.size(), equalTo(1));
	}
	
	@Test
	public void givenACardLibrary_IcanFilterByCardSet_secondExample() {
		Criteria filter = new CardSetCriteria(CardSet.SECOND_THOUGHTS);
		
		List<Card> filtered = filter.filter(getSampleCardList());
		
		assertThat(filtered.size(), equalTo(0));
	}
}
