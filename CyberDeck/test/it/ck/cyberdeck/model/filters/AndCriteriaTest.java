package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.CardSet;

import mockit.Expectations;
import mockit.Injectable;

import org.junit.Test;

public class AndCriteriaTest {

	@Injectable
	private CardLibrary library;

	private Criteria second = new NameCriteria("cardName");
	private Criteria first = new NameCriteria("cardName");

	@Test
	public void test() {
		
		new Expectations() {
			{
				library.getCardList(); times = 2;
				result = getSampleCardList();
			}
		};
		AndCriteria and = new AndCriteria(first, second);
		List<Card> filtered = and.filter(library);
		assertThat(filtered.size(), equalTo(0));
	}
	
	private Object getSampleCardList() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card("cardName", null, null, 0, new CardKey(CardSet.CORE, 1)));
		cards.add(new Card("anotherCard", null, null, 0, new CardKey(CardSet.CORE, 2)));
		return cards;
	}

}
