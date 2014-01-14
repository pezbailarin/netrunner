package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;

import java.util.List;

import org.junit.Test;

public class AndCriteriaTest extends ChainedCriteriaTest 
{
	@Test
	public void givenAListOfCards_andTwoCriteria_IobtainTheAndListOfTheTwoCriteria() {
		List<Card> cardList = getSampleCardList();
		expectedCriteriaCalls(cardList, getSampleSingleResult());
		AndCriteria and = new AndCriteria(first, second);
		
		List<Card> filtered = and.filter(cardList);
		
		assertThat(filtered.size(), equalTo(0));
	}

}
