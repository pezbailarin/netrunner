package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;

import java.util.ArrayList;
import java.util.List;

import mockit.Expectations;
import mockit.Injectable;

import org.junit.Test;

public class NameCriteriaTest {

	@Injectable
	private CardLibrary library;

	@Test
	public void givenACardLibrary_IcanFilterByName() {

		new Expectations() {
			{
				library.getCardList();
				result = getSampleCardList();
			}
		};

		Criteria filter = new NameCriteria("cardName");
		List<Card> filtered = filter.filter(library);
		assertThat(filtered.size(), equalTo(1));
		assertThat(filtered.get(0).getName(), equalTo("cardName"));
	}

	private Object getSampleCardList() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card("cardName", null, null, 0, null));
		cards.add(new Card("filteredOut", null, null, 0, null));
		return cards;
	}

}