package com.xspeedit.robot.algorithmes;

import com.xspeedit.robot.Constantes;

/**
 * <b>Algorithme glouton (next fit)</b> conforme à l'énoncé.
 * <br><br> On ajoute successivement dans le carton tant que l'on peut.
 * En cas de dépacement de la capacité {@link Constantes#CAPACITE_MAX}, on utilise un nouveau carton.
 *
 * Il s'agit d'une méthode heuristique fournissant une solution réalisable (pas forcément optimale) très rapidement [complexité O(n)].
 * La solution trouvée constitue une borne supérieure pour notre problème.
 *
 * @author fabienrecco
 *
 */
public final class AlgorithmeGlouton implements IAlgorithme {

    @Override
    public String resoudre(final int[] articles) {
        final StringBuilder sb = new StringBuilder();
        if(articles != null) {
            int capacity = Constantes.CAPACITE_MAX;
            for(int i : articles) {
                if(capacity - i < 0) {
                    sb.append(Constantes.SEPARATEUR);
                    capacity = Constantes.CAPACITE_MAX;
                }
                sb.append(i);
                capacity -= i;
            }
        }
        return sb.toString();
    }

}
