package it.ck.cyberdeck.model.filters;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardData;
import it.ck.cyberdeck.model.CardSet;
import it.ck.cyberdeck.model.CardType;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.model.Side;

import java.util.ArrayList;
import java.util.List;

public abstract class CriteriaTest {

	protected List<Card> getSampleCardList() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(buildFakeCardData(0, 0, Faction.ANARCH, "cardName", 1, CardSet.CORE, Side.RUNNER, "subtype", CardType.EVENT)));
		cards.add(new Card(buildFakeCardData(0, 0, Faction.HAAS_BIOROID, "another", 2, CardSet.A_STUDY_IN_STATIC, Side.CORP, "sub", CardType.OPERATION)));
		return cards;
	}

	private CardData buildFakeCardData(int agendaPoints, int count, Faction faction, String cardName, int num, CardSet cardSet, Side side, String subtype, CardType cardType) {
		CardData data = new CardData();
		data.agendapoints = agendaPoints;
		data.cost = null;
		data.count = count;
		data.errata = null;
		data.identity = faction;
		data.name = cardName;
		data.num = num;
		data.set = cardSet;
		data.side = side;
		data.subtype = subtype;
		data.type = cardType;
		return data;
	}

}
