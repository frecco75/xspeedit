package com.xspeedit.robot.algorithmes;

import com.xspeedit.robot.domain.Article;
import com.xspeedit.robot.domain.Packing;
import io.vavr.collection.List;
import lombok.val;

import static io.vavr.API.List;

/**
 * Packing optimale au problème <u>bin packing</u> basée sur l'algorithme <i>branch & bound</i>
 *
 * @author fabienrecco
 */
public final class AlgorithmeOptimise implements IAlgorithme {

    @Override
    public Packing solve(final Iterable<Article> articles) {
        val articleList = List.ofAll(articles);
        return explore(
                new Packing().newBin(articleList.head()),
                articleList.pop(),
                trivial(articleList)
        );
    }

    private Packing trivial(final List<Article> articles) {
        return articles
                .foldLeft(new Packing(), Packing::newBin);
    }

    private Packing explore(final Packing packing, final List<Article> articles, final Packing temporaryBest) {
        return articles
                .headOption()
                .map(a -> {
                            /* A) met l'article a dans chaque bin et explore(#a+1) */
                            val bestPacking = packing.getBins()
                                    .foldLeft(
                                            List(temporaryBest),
                                            (tmp, bin) -> packing.add(bin, a)
                                                    .map(newPacking -> explore(newPacking, articles.pop(), temporaryBest))
                                                    .fold(err -> tmp, tmp::append))
                                    .reduce(Packing::best);

                            /* B) met l'article a dans un nouveau bin et explore(#a+1) */
                            return packing.getBins().size() < bestPacking.getBins().size() - 1 ?
                                    explore(packing.newBin(a), articles.pop(), bestPacking) :
                                    bestPacking;
                        }
                )
                .getOrElse(() -> packing.best(temporaryBest));
    }

}
