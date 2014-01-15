package it.ck.cyberdeck.model.filters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;

import java.util.List;

import org.junit.Test;

public class SubtypeCriteriaTest extends CriteriaTest {

	@Test
	public void givenACardLibrary_IcanFilterBySubtype() {
		Criteria filter = new SubtypeCriteria("sub");
		
		List<Card> filtered = filter.filter(getSampleCardList());
		
		assertThat(filtered.size(), equalTo(2));
	}
}
