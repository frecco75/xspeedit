package com.xspeedit.robot.algorithmes;

import com.xspeedit.robot.Constants;
import com.xspeedit.robot.domain.Article;
import com.xspeedit.robot.domain.Packing;
import io.vavr.collection.List;

/**
 * <b>Algorithme glouton (next fit)</b> conforme à l'énoncé.
 * <br><br> On ajoute successivement dans le bin tant que l'on peut.
 * En cas de dépacement de la capacité {@link Constants#CAPACITY_MAX}, on utilise un nouveau bin.
 * <p>
 * Il s'agit d'une méthode heuristique fournissant une solution réalisable (pas forcément optimale) très rapidement [complexité O(n)].
 * La solution trouvée constitue une borne supérieure à notre problème.
 *
 * @author fabienrecco
 */
public final class AlgorithmeGlouton implements IAlgorithme {

    @Override
    public Packing solve(final Iterable<Article> articles) {
        return List.ofAll(articles)
                .foldLeft(new Packing(), this::add);
    }

    private Packing add(final Packing packing, final Article article) {
        return packing.getBins()
                .lastOption()
                .flatMap(bin -> packing.add(bin, article).toOption())
                .getOrElse(() -> packing.newBin(article));
    }

}
