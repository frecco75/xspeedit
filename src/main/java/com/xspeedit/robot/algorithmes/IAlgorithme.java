package com.xspeedit.robot.algorithmes;

import com.xspeedit.robot.Constants;
import com.xspeedit.robot.domain.Article;
import com.xspeedit.robot.domain.Packing;
import com.xspeedit.robot.domain.Bin;

public interface IAlgorithme {

    /**
     * Algorithme qui détermine la répartition des {@link Article} dans des {@link Bin}
     *
     * @param articles articles à emballer, représentés par leur <b>taille</b>
     * @return retourne les articles rangés dans N bins de capacité {@link Constants#CAPACITY_MAX}
     */
    Packing solve(final Iterable<Article> articles);

}
