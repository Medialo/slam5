package com.example.td9;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import android.content.res.Resources;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TestMainActivitySurAVD
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getEntreeLitValeurTapee()
    {
        // arrange : récupérer l'activité
        final MainActivity activity = mActivityRule.getActivity();
        // act : taper une valeur et appeler getEntree()
        onView(withId(R.id.entree)).perform(typeText("6542.541"), closeSoftKeyboard());
        double entree = activity.getEntree();
        // assert : vérifier que le résultat de getEntree() est le nombre tapé
        assertEquals(entree,6542.541,0.01);
    }

    @Test
    public void putSortieAfficheNombre() throws Throwable {
        // arrange : récupérer l'activité
        // act : demander à l'activité d'afficher un nombre
        mActivityRule.runOnUiThread(() -> {
            // TODO appeler putSortie de activity, avec 16512.6532
            mActivityRule.getActivity().putSortie(16512.6532);
        });
//        mActivityRule.getActivity().putSortie(16512.6532);
        // assert : vérifier le résultat affiché
        onView(ViewMatchers.withId(R.id.sortie)).check(matches(withText("16512,653"
        )));
    }

    @Test
    public void ressourcesDeMemeTaille()
    {
        // arrange : activité et gestionnaire de ressources
        MainActivity activity = mActivityRule.getActivity();
        Resources resources = activity.getResources();
        Convertisseur convertisseur = activity.getConvertisseur();
        // act : récupérer les ressources
        String[] conversions = resources.getStringArray(R.array.conversions);
        // assert : vérifier et comparer les tailles des ressources au convertisseur
        assertEquals(conversions.length - 1,convertisseur.getCodeConversionMax() + 1);
    }

    @Test
    public void choixConversionCodeCorrect()
    {
        // arrange : convertisseur
        MainActivity activity = mActivityRule.getActivity();
        Convertisseur convertisseur = activity.getConvertisseur();
        // act : choisir la conversion psi->bar dans le spinner 0="choisir", 1="inch->cm", 2="psi->bar"
        onView(withId(R.id.conversion)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        // assert : vérifier que la bonne conversion est affichée
        onView(withId(R.id.conversion)).check(matches(withSpinnerText("psi -> bar")));
        // assert : vérifier que le code du convertisseur est le choisi
        assertEquals(convertisseur.getCodeConversion(),2);
    }

    @Test
    public void conversionComplete() {
        MainActivity activity = mActivityRule.getActivity();
        Convertisseur convertisseur = activity.getConvertisseur();
        // act : mettre "10" dans le EditText d'entrée
        onView(withId(R.id.entree)).perform(typeText("10"),
                closeSoftKeyboard());
        // act : choisir la conversion inch->cm dans le spinner
        onView(withId(R.id.conversion)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        // assert : vérifier que la bonne conversion est sélectionnée
        onView(withId(R.id.conversion)).check(matches(withSpinnerText("inch -> cm")));
        assertEquals(convertisseur.getCodeConversion(),1);
        // act : cliquer sur le bouton Convertir
        activity.onConvertir(null);
        // assert : vérifier le résultat affiché
        onView(withId(R.id.sortie)).check(matches(withText("0,689")));
    }

}





























//
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.pressBackUnconditionally;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//
//@RunWith(AndroidJUnit4.class)
//public class TestMainActivitySurAVD
//{
//    // définition du scénario de ces tests : lancer MainActivity
//    public @Rule ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);
//    // activité en cours de test (voir la 1e ligne arrange des tests)
//    private MainActivity activity;
//
//    @Before
//    public void initEachTest() {
//// récupérer l'activité
//        activityScenarioRule.getScenario().onActivity(a -> activity = a);
//    }
//// méthodes de test...
//
//    @Test
//    public void boutonBackFermeActivite()
//    {
//// arrange
//// act : appuyer sur le bouton back
//        pressBackUnconditionally();
//// assert : activité fermée (destroyed)
//        assertThat(activityScenarioRule.getScenario().getState(),
//                equalTo(Lifecycle.State.DESTROYED));
//    }
//
//
//}
