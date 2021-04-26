package com.example.td9;

import android.content.Context;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.td9", appContext.getPackageName());
    }


    // activité espionnée
    @Spy
    final MainActivity activity = new MainActivity();
    // fausses vues de l'interface
    @Mock
    EditText etEntreeMock;
    @Mock
    TextView tvSortieMock;
    @Mock
    Spinner spChoixConvertionMock;
    @Before
    public void initEachTest()
    {
        // lier les ressources et les vues
        doReturn(etEntreeMock).when(activity).findViewById(R.id.entree);
        doReturn(tvSortieMock).when(activity).findViewById(R.id.sortie);

        doReturn(spChoixConvertionMock).when(activity).findViewById(R.id.conversion
        );
        activity.findViews();

    }

    /*@Test
    public void getEntreeLitValeurSaisie()
    {
        // arrange : placer une chaîne représentant un nombre correct dans
        etEntreeMock.setText("12345.6789");
        final EditText input = Mockito.mock(EditText.class);
        when(etEntreeMock.getText()).thenReturn(new FakeEditable("7 nains"));
        // act : appeler getEntree()

        // assert : vérifier que le résultat de getEntree() est le nombre voulu
    }*/


    @RunWith(MockitoJUnitRunner.class)
    public class TestsMainActivity
    {
        // activité espionnée
        @Spy final MainActivity activity = new MainActivity();
        // fausses vues de l'interface
        @Mock EditText etEntreeMock;
        @Mock TextView tvSortieMock;
        @Mock Spinner spChoixConvertionMock;
        @Before
        public void initEachTest()
        {
            // lier les ressources et les vues
            doReturn(etEntreeMock).when(activity).findViewById(R.id.entree);
            doReturn(tvSortieMock).when(activity).findViewById(R.id.sortie);

            doReturn(spChoixConvertionMock).when(activity).findViewById(R.id.conversion
            );
            activity.findViews();
        }
    }

  /*  @Test
    public void getEntreeThrowNumberFormatException()
    {
        // arrange : placer une chaîne incorrecte dans
        (etEntreeMock.getText()).thenReturn(new FakeEditable("7 nains"));
        // act : appeler getEntree()
        // pas d'assert mais un paramètre expected dans @Test
    }*/


}