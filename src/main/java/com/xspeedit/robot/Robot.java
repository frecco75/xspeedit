package com.xspeedit.robot;

import com.xspeedit.robot.algorithmes.IAlgorithme;

/**
 * Robot
 * @author fabienrecco
 *
 */
public final class Robot {


    // Attributes
    //--------------------------------------------------------

    // design pattern strategy pour pouvoir changer d'algorithme facilement
    private final IAlgorithme algorithme;


    // Constructor
    //--------------------------------------------------------

    public Robot(final IAlgorithme algorithme) {
        this.algorithme = algorithme;
    }


    // Public methods
    //--------------------------------------------------------

    public String emballe(final String articles) {
        String solution = "";
        final int[] arr = toArrayOfInt(articles);
        if(arr != null && arr.length > 0) {
            solution = algorithme.resoudre(arr);
        }
        return solution;
    }


    // Utilities
    //--------------------------------------------------------

    /**
     * Transforme une chaîne numérique en tableau d'entiers.
     * @param articles
     * @throws IllegalArgumentException si la chaîne n'est pas numérique.
     * @return
     */
    private static int[] toArrayOfInt(final String articles) {
        int[] arr = null;
        if(articles != null) {
            arr = new int[articles.length()];
            for (int i=0; i<articles.length(); ++i) {
                char c = articles.charAt(i);
                if(! Character.isDigit(c)) {
                    throw new IllegalArgumentException(articles + " is not a number!");
                }
                arr[i] = c-'0';
            }
        }
        return arr;
    }

}
