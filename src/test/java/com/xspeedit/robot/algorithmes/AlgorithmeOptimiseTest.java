package com.xspeedit.robot.algorithmes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.xspeedit.robot.Constantes;

public final class AlgorithmeOptimiseTest {

    private final IAlgorithme algorithme = new AlgorithmeOptimise();

    @Test
    public void algorithm_test() {
        final int[] given = new int[]{1,6,3,8,4,1,6,8,9,5,2,5,7,7,3};
        final String expected = "163/82/46/19/8/55/73/7";
        final String actual = algorithme.resoudre(given);

        // Il peut y avoir plusieurs solutions optimales
        // Pour vérifier si la solution calculée est optimale, il faut vérifier le nombre de cartons utilisés
        verifierNombreCartons(expected, actual);
        assertTrue(expected.length() == actual.length()); // petite astuce qui revient au même (les longueurs des chaines sont égales ssi même nombre de cartons)
    }


    // Private methods
    //---------------------------------------------------

    private void verifierNombreCartons(final String expected, final String actual) {
        int nbrCartonsSolutionOptimale = expected.split(Constantes.SEPARATEUR).length;
        int nbrCartonsSolutionCalculee = actual.split(Constantes.SEPARATEUR).length;
        assertEquals(nbrCartonsSolutionOptimale, nbrCartonsSolutionCalculee);
    }

}
