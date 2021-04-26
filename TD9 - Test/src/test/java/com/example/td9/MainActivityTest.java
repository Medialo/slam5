package com.example.td9;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

//
//public class MainActivityTest extends TestCase {
//
//    public void testGetEntree() {
//    }
//
//    public void testPutSortie() {
//    }
//
//    public void testPutSortieError() {
//    }
//
//    public void testOnConvertir() {
//    }
//}

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest
{
    // activité espionnée
    @Spy
    final MainActivity activity = new MainActivity();
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
        doReturn(spChoixConvertionMock).when(activity).findViewById(R.id.conversion);
        activity.findViews();
    }

    @Test
    public void getEntreeLitValeurSaisie()
    {
        // arrange : placer une chaîne représentant un nombre correct dansetEntreeMock
        when(etEntreeMock.getText()).thenReturn(new FakeEditable("12345.6789"));
        // act : appeler getEntree()
        double d = activity.getEntree();
        // assert : vérifier que le résultat de getEntree() est le nombre voulu
        assertEquals(d,12345.6789,0.1);
    }

    @Test(expected = NumberFormatException.class)
    public void getEntreeThrowNumberFormatException()
    {
// arrange : placer une chaîne incorrecte dans entreeMock
        when(etEntreeMock.getText()).thenReturn(new FakeEditable("7 nains"));
        // act : appeler getEntree() de activity
        double d = activity.getEntree();
// pas d'assert mais un paramètre expected dans @Test
    }

    @Test
    public void putSortieEcritErreur()
    {
// arrange : rien
// act : appeler putSortieError()
        activity.putSortieError();
// assert : sortieMock.setText a été appelé avec la chaîne voulue
        verify(tvSortieMock).setText(R.string.erreur_saisie);
    }

    @Test
    public void putSortieEcritValeurVoulue()
    {
// arrange : rien
// act : appeler putSortie avec un nombre assez compliqué, mais pas trop
        activity.putSortie(50.000);
// assert : sortieMock.setText a été appelé avec la chaîne voulue
        verify(tvSortieMock).setText("50,000");
    }

    @Test
    public void convertirDoitConvertir(){
        // arrange : mettre en mode psi->bar
// mieux: faire ça avec le Spinner, mais c'est trop complexe
        activity.getConvertisseur().setCodeConversion(1);
// arrange : placer -7.25 dans entreeMock
  when(etEntreeMock.getText()).thenReturn(new FakeEditable("-7.25"));
// act : appeler onConvertir(null)
        activity.onConvertir(null);
// assert : sortieMock.setText a été appelé avec la chaîne voulue, "-0,500"
        verify(tvSortieMock).setText("-0,500");
    }
}