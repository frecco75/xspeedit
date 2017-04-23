package com.xspeedit.robot.algorithmes;

import com.xspeedit.robot.Constantes;

public interface IAlgorithme {

    /**
     * Algorithme qui détermine la répartition des articles dans des cartons
     *
     * @param articles articles à emballer, représentés par leur <b>taille</b>
     * @return retourne les articles rangés dans N cartons de capacité {@link Constantes#CAPACITE_MAX}, séparés par {@link IAlgorithme#SEPARATEUR}
     */
     String resoudre(final int[] articles);

}
