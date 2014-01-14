package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;

import java.util.List;


import org.junit.Test;

public class OrCriteriaTest extends ChainedCriteriaTest 
{
	@Test
	public void givenAListOfCards_andTwoCriteria_IobtainTheOrListOfTheTwoCriteria() {
		List<Card> cardList = getSampleCardList();
		expectedCriteriaCalls(cardList, cardList);
		OrCriteria or = new OrCriteria(first, second);

		List<Card> filtered = or.filter(cardList);
		
		assertThat(filtered.size(), equalTo(1));
		assertThat(filtered.get(0).getName(), equalTo("cardName"));
	}
}
