package it.ck.cyberdeck.model;

import it.ck.cyberdeck.model.group.ElementGroup;
import it.ck.cyberdeck.model.reputation.ReputationRule;
import it.ck.cyberdeck.model.reputation.ReputationRuleFactory;
import it.ck.cyberdeck.model.utils.CardKeyComparator;
import it.ck.cyberdeck.model.utils.CardNameComparator;

import java.util.*;

public class AndroidNetrunnerCardLibrary implements CardLibrary {

	private Map<CardKey, Card> cards = new HashMap<CardKey, Card>();
	private ReputationRuleFactory reputationRuleFactory;

	public AndroidNetrunnerCardLibrary(ReputationRuleFactory reputationRuleFactory) {
		this.reputationRuleFactory = reputationRuleFactory;
	}

	@Override
	public List<Card> getCardList() {
		List<Card> cardList = new ArrayList<Card>();
		cardList.addAll(cards.values());
		Collections.sort(cardList, new CardKeyComparator());
		return Collections.unmodifiableList(cardList);
	}

	@Override
	public List<Identity> getIdentities() {

		List<Identity> identities = new ArrayList<Identity>();
		for (Card card : cards.values()) {
			if (card.isIdentity()){
				CardKey key = card.getKey();
				identities.add(new Identity(card, getReputationRule(key)));
			}
		}
		return Collections.unmodifiableList(identities);
	}

	private ReputationRule getReputationRule(CardKey key) {
		return reputationRuleFactory.createRule(key);
	}

	@Override
	public List<Card> getCardList(Identity identity) {
		List<Card> result = new ArrayList<Card>();
		for (Card card : cards.values()) {
			if (identity.canUse(card)) {
				result.add(card);
			}
		}
		return result;
	}

	@Override
	public void addAll(List<Card> cardList) {
		for (Card card : cardList) {
			cards.put(card.getKey(), card);
		}
	}

	@Override
	public List<ElementGroup<Card>> getCardGroups(Side side) {
		Map<CardType, ElementGroup<Card>> cardGroups = new HashMap<CardType, ElementGroup<Card>>();
		for (Card card : cards.values()) {
			if (card.getSide().equals(side)) {
				ElementGroup<Card> cardGroup = cardGroups.get(card.getType());
				if (cardGroup == null) {
					cardGroup = new ElementGroup<Card>(card.getType(), new CardNameComparator());
				}
				cardGroup.add(card);
				cardGroups.put(card.getType(), cardGroup);
			}
		}

		return Collections.unmodifiableList(new ArrayList<ElementGroup<Card>>(cardGroups
				.values()));

	}

	@Override
	public List<ElementGroup<Card>> getCardGroups(Identity identity) {
		Map<CardType, ElementGroup<Card>> cardGroups = populateCardGroup(identity);

		return Collections.unmodifiableList(new ArrayList<ElementGroup<Card>>(cardGroups.values()));
	}
	
	@Override
	public List<ElementGroup<Card>> getCardGroupsWithoutIdentities(Identity identity){
		Map<CardType, ElementGroup<Card>> cardGroups = populateCardGroup(identity);
		cardGroups.remove(CardType.IDENTITY);
		return Collections.unmodifiableList(new ArrayList<ElementGroup<Card>>(cardGroups.values()));
	}

	private Map<CardType, ElementGroup<Card>> populateCardGroup(Identity identity) {
		Map<CardType, ElementGroup<Card>> cardGroups = new HashMap<CardType, ElementGroup<Card>>();
		for (Card card : cards.values()) {
			if (card.getSide().equals(identity.side())) {
				ElementGroup<Card> cardGroup = cardGroups.get(card.getType());
				if (cardGroup == null) {
					cardGroup = new ElementGroup<Card>(card.getType(), new CardNameComparator());
				}
				if (identity.canUse(card)) {
					cardGroup.add(card);
					cardGroups.put(card.getType(), cardGroup);
				}
			}
		}
		return cardGroups;
	}

	@Override
	public Card getCard(CardKey key) {
		return cards.get(key);
	}

	@Override
	public Identity getItentity(CardKey key) {
		return new Identity(getCard(key), getReputationRule(key));
	}
}
