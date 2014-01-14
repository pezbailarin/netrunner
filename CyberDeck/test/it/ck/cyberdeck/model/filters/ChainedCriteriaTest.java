package it.ck.cyberdeck.model.filters;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.model.CardSet;

import java.util.ArrayList;
import java.util.List;

import mockit.Expectations;
import mockit.Injectable;

public abstract class ChainedCriteriaTest extends CriteriaTest {

	@Injectable
	protected Criteria first;
	@Injectable
	protected Criteria second;
	
	protected List<Card> getSampleSingleResult() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card("cardName", null, null, 0, new CardKey(CardSet.CORE, 1)));
		return cards;
	}

	protected void expectedCriteriaCalls(final List<Card> firstCriteriaParameter, final List<Card> secondCriteriaParameter) {
		new Expectations() {{
			first.filter(firstCriteriaParameter); returns(getSampleSingleResult(), (Object[]) null);

			second.filter(secondCriteriaParameter); returns(new ArrayList<>(), (Object[]) null);
		}};
	}

}
