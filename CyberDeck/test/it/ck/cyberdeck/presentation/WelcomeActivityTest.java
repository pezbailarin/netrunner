package it.ck.cyberdeck.presentation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;
import it.ck.cyberdeck.R;
import it.ck.cyberdeck.presentation.activity.CardListActivity;
import it.ck.cyberdeck.presentation.activity.DeckListActivity;
import it.ck.cyberdeck.presentation.activity.NewDeckActivity;
import it.ck.cyberdeck.presentation.activity.WelcomeActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import android.content.Intent;
import android.widget.Button;

@RunWith(RobolectricTestRunner.class)
public class WelcomeActivityTest {

	private WelcomeActivity activity;
	private Button buttonLibrary;
	private Button buttonNewDeck;
	private Button buttonDeckList;

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(WelcomeActivity.class).create().get();
		buttonLibrary = (Button) activity.findViewById(R.id.button_library);
		buttonNewDeck = (Button) activity.findViewById(R.id.button_new_deck);
		buttonDeckList = (Button) activity.findViewById(R.id.button_deck_list);
	}

	@Test
	public void theWelcomeActivityHasThreeButtons() {
		assertThat(buttonLibrary, is(not(nullValue())));
		assertThat(buttonNewDeck, is(not(nullValue())));
		assertThat(buttonDeckList, is(not(nullValue())));
	}

	@Test
	public void pressingTheButtonCardLibraryShouldStartTheCardLibraryActivity() {
		buttonLibrary.performClick();
		ShadowActivity shadowActivity = shadowOf(activity);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = shadowOf(startedIntent);
		assertThat(shadowIntent.getComponent().getClassName(), equalTo(CardListActivity.class.getName()));
	}
	
	@Test
	public void pressingTheButtonDeckListShouldStartTheDeckListActivity() {
		buttonDeckList.performClick();
		ShadowActivity shadowActivity = shadowOf(activity);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = shadowOf(startedIntent);
		assertThat(shadowIntent.getComponent().getClassName(), equalTo(DeckListActivity.class.getName()));
	}
	
	@Test
	public void pressingTheButtonNewDeckShouldStartTheDNewDeckWizardActivity() {
		buttonNewDeck.performClick();
		ShadowActivity shadowActivity = shadowOf(activity);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = shadowOf(startedIntent);
		assertThat(shadowIntent.getComponent().getClassName(), equalTo(NewDeckActivity.class.getName()));
	}

}
