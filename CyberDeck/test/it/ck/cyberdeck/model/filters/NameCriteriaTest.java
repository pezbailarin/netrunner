package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;

import java.util.List;

import org.junit.Test;

public class NameCriteriaTest extends CriteriaTest 
{
	@Test
	public void givenACardLibrary_IcanFilterByName() {
		Criteria filter = new NameCriteria("cardName");
		
		List<Card> filtered = filter.filter(getSampleCardList());
		
		assertThat(filtered.size(), equalTo(1));
		assertThat(filtered.get(0).getName(), equalTo("cardName"));
	}
}