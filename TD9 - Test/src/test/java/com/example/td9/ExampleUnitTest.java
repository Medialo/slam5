package com.example.td9;

import org.junit.Test;
import org.junit.runner.RunWith;



import java.util.Arrays;
import java.util.Collection;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnitParamsRunner.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Parameters
    @Test
    public void getter(final int codeSet)
    {
        Convertisseur convertisseur = new Convertisseur();
//        System.out.println(convertisseur.getCodeConversionMax());
        if(codeSet > convertisseur.getCodeConversionMax()){
            assertThrows(IllegalArgumentException.class,() ->
                    convertisseur.setCodeConversion(codeSet));
        } else {
            convertisseur.setCodeConversion(codeSet);
            int codeGet = convertisseur.getCodeConversion();
            assertEquals(codeSet,codeGet);
        }
    }
    @SuppressWarnings("unused")
    static Collection<Object[]> parametersForGetter() {
        return Arrays.asList(new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}
        });
    }


    @Test
    public void constructeurDoitInitCodeAZero()
    {
        Convertisseur convertisseur = new Convertisseur();

        int code = convertisseur.getCodeConversion();

        assertEquals("le code initial doit être 0", 0, code);
        // variante avec assertThat (Hamcrest)
        //assertThat("le code initial doit être 0", code, equalTo(0));
    }

    @Test
    public void convert_inch_cm(){
        Convertisseur convertisseur = new Convertisseur();
        assertEquals(0,convertisseur.convertir(0.0),0);
        assertEquals(2.54,convertisseur.convertir(1.0),0);
        assertEquals(33.02,convertisseur.convertir(13.0),0);
        assertEquals(-2.9972,convertisseur.convertir(-1.18),0);
    }

    @Test
    public void convert_psi_bar(){
        Convertisseur convertisseur = new Convertisseur();
        convertisseur.setCodeConversion(1);
        assertEquals(0,convertisseur.convertir(0.0),0);
        assertEquals(0.0689,convertisseur.convertir(1),0.0001);
        assertEquals(3.0337,convertisseur.convertir(44),0.0001);
        assertEquals(-0.50,convertisseur.convertir(-7.25),0.01);
    }

    @Test
    public void convert_mph_kmh(){
        Convertisseur convertisseur = new Convertisseur();
        convertisseur.setCodeConversion(2);
        assertEquals("test",0,convertisseur.convertir(0),0.0001);
        assertEquals(1.609,convertisseur.convertir(1),0.001);
        assertEquals(257.495,convertisseur.convertir(160),0.0001);
        assertEquals(-60,convertisseur.convertir(-37.28),0.01);
    }

}