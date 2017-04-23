package com.xspeedit.robot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xspeedit.robot.algorithmes.AlgorithmeGlouton;

public class RobotTest {

    private final Robot robot = new Robot(new AlgorithmeGlouton());

    @Test
    public void null_argument_test() {
        final String actual = robot.emballe(null);
        assertEquals("", actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void bad_argument_test() {
        final String given = "1ssa9";
        robot.emballe(given); // Should throw IllegalArgumentException because "1ssa9" is not a number
    }

    /**
     * Test d'intégration pour vérifier la sortie attendue sur un cas concret
     */
    @Test
    public void algorithm_test() {
        final String given = "163841689525773";
        final String expected = "163/8/41/6/8/9/52/5/7/73";
        final String actual = robot.emballe(given);
        assertEquals(expected, actual);
    }
}
