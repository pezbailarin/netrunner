package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;

import mockit.Expectations;
import mockit.Injectable;

import org.junit.Test;

public class OrCriteriaTest {
	
	@Injectable
	private CardLibrary library;

	private Criteria first = new NameCriteria("notExistent");
	private Criteria second = new NameCriteria("cardName");

	@Test
	public void test() {
		
		new Expectations() {
			{
				library.getCardList(); times = 2;
				result = getSampleCardList();
			}
		};
		
		OrCriteria or = new OrCriteria(first, second);
		List<Card> filtered = or.filter(library);
		
		assertThat(filtered.size(), equalTo(1));
		assertThat(filtered.get(0).getName(), equalTo("cardName"));
	}
	
	private Object getSampleCardList() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card("cardName", null, null, 0, null));
		cards.add(new Card("anotherCard", null, null, 0, null));
		return cards;
	}
}
