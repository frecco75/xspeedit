package com.xspeedit.robot.algorithmes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class AlgorithmeGloutonTest {

    private final IAlgorithme algorithme = new AlgorithmeGlouton();

    @Test
    public void algorithm_test() {
        final int[] given = new int[] {1,6,3,8,4,1,6,8,9,5,2,5,7,7,3};
        final String expected = "163/8/41/6/8/9/52/5/7/73";
        final String actual = algorithme.resoudre(given);
        assertEquals(expected, actual);
    }

}
