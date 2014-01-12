package it.ck.cyberdeck.model;

import it.ck.cyberdeck.model.group.ElementGroup;

import java.util.List;

public interface CardLibrary {

	List<Card> getCardList();

	List<Identity> getIdentities();

	List<Card> getCardList(Identity identity);

	void addAll(List<Card> cardList);

	List<ElementGroup<Card>> getCardGroups(Side side);

	List<ElementGroup<Card>> getCardGroups(Identity identity);

	List<ElementGroup<Card>> getCardGroupsWithoutIdentities(Identity identity);

	Card getCard(CardKey key);

	Identity getItentity(CardKey key);

}