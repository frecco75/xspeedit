package com.xspeedit.robot.algorithmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xspeedit.robot.Constantes;

/**
 * Solution optimale au problème <u>bin packing</u> basée sur l'algorithme <i>branch & bound</i>
 * @author fabienrecco
 *
 */
public final class AlgorithmeOptimise implements IAlgorithme {

    @Override
    public String resoudre(final int[] articles) {
        String solution = "";
        if(articles != null) {
            final Tampon tampon = new Tampon(articles);

            // on ajoute le 1er article au 1er carton
            creerNouveauCarton(tampon);
            ajouter(tampon, 0, 0);

            // exploration recursive
            explore(tampon, 1);
            solution = tampon.obtenirSolution();
        }
        return solution;
    }


    // Private methods
    //--------------------------------------------------------

    /**
     * Méthode d'exploration de l'arbre de solutions.
     * <br>
     * <br>
     * Algorithme :
     * <pre>
     * paramètre: #i numéro de l'article
     *
     * DEBUT explorer(#i)
     *     A)
     *     Pour chaque carton #c
     *         si l'article #i rentre dans le carton #c alors
     *             ajouter #i à #c
     *             explorer(#i+1)
     *             retirer #i de #c
     *         fin si
     *     Fin Pour
     *
     *     B)
     *     Créer un nouveau carton #c
     *     affecter #i à #c
     *     explorer(#i+1)
     *     retirer #i de #c
     *     Supprimer le nouveau carton #c
     * FIN
     * </pre>
     *
     * @param tampon
     * @param i numéro de l'article
     */
    private void explore(final Tampon tampon, final int i) {
        // sortie de la recursion lorsque l'on a traité tous les articles
        if (i == tampon.articles.length) {
            if (tampon.cartonNbr < tampon.cartonLimite) { // Cette solution est meilleure -> on change la borne supérieure
                enregistrerMeilleureSolution(tampon);
            }
            return;
        }

        /** A) met l'article i dans chaque carton et explore(#i+1) **/
        for (int carton = 0; carton < tampon.cartonNbr; ++carton) {
            // Si l'article i rentre dans le carton
            if (tampon.remplissageCarton[carton] + tampon.articles[i] <= Constantes.CAPACITE_MAX) {
                ajouter(tampon, i, carton);
                explore(tampon, i+1);
                retirer(tampon, i, carton);
            }
        }

        /** B) met l'article i dans un nouveau carton et explore(#i+1) **/
        // On utilise la borne supérieure -> inutile d'explorer les solutions qui seront forcément moins bonnes
        if (tampon.cartonNbr < tampon.cartonLimite) {
            // met l'article i dans un nouveau carton
            creerNouveauCarton(tampon);
            ajouter(tampon, i, tampon.cartonNbr-1);
            explore(tampon, i+1);
            retirer(tampon, i, tampon.cartonNbr-1);
            supprimerNouveauCarton(tampon);
        }
    }


    // Fonctions utilitaires
    //--------------------------------------------------------

    private void creerNouveauCarton(final Tampon tampon) {
        tampon.cartonNbr++;
        tampon.remplissageCarton[tampon.cartonNbr-1] = 0;
    }

    private void supprimerNouveauCarton(final Tampon tampon) {
        tampon.remplissageCarton[tampon.cartonNbr-1] = 0;
        tampon.cartonNbr--;
    }

    private void ajouter(final Tampon tampon, final int i, final int carton) {
        tampon.X[i] = carton;
        tampon.remplissageCarton[carton] += tampon.articles[i];
    }

    private void retirer(final Tampon tampon, final int i, final int carton) {
        tampon.X[i] = -1;
        tampon.remplissageCarton[carton] -= tampon.articles[i];
    }

    private void enregistrerMeilleureSolution(final Tampon tampon) {
        // On change la borne supérieure -> devient le nombre de cartons trouvés dans cette solution
        tampon.cartonLimite = tampon.cartonNbr;

        // Mise à jour de la répartition des articles [bestX] de la meilleure solution
        tampon.bestX = new int[tampon.articles.length];
        for(int tmp=0; tmp<tampon.X.length; ++tmp) {
            tampon.bestX[tmp] = tampon.X[tmp];
        }
    }


    // Inner class
    //--------------------------------------------------------

    /**
     * Classe servant à stocker les données temporaires calculées.
     * <br>La meilleure solution trouvée à l'instant t est représentée par {@link #bestX}.
     * @author fabienrecco
     *
     */
    private static class Tampon {

        /**------------**/
        /** Attributes **/
        /**------------**/

        final int[] articles;     // articles à ranger
        int cartonNbr;            // Nombre de cartons utilisés par la solution courante
        int[] remplissageCarton;  // taille de chaque carton (niveau de remplissage)
        int[] X;                  // les affectations article/carton

        // Meilleure solution = borne supérieure
        int cartonLimite;         // limite du nombre de cartons = nombre d'articles de la meilleure solution
        int[] bestX;              // meilleure solution connue de partitionnement


        /**-------------**/
        /** Constructor **/
        /**-------------**/

        public Tampon(final int[] articles) {
            this.articles = articles;
            int articlesNbr = articles.length;
            cartonLimite = articlesNbr; // borne supérieure : 1 article par carton qui est une solution réalisable triviale
            remplissageCarton = new int[articlesNbr];
            X = new int[articlesNbr];
        }


        /**--------------**/
        /**   Méthodes   **/
        /**--------------**/

        /**
         * Construit la sortie attendue (répartition d'articles) en fonction de la solution optimale connue {@link #bestX}.
         * @param tampon
         * @return
         */
        public String obtenirSolution() {
            Map<Integer, List<Integer>> cartons = new HashMap<>();
            for(int k=0; k<bestX.length; ++k) {
                int cartonNbr = bestX[k];
                List<Integer> carton = cartons.get(cartonNbr);
                if(carton == null) {
                    carton = new ArrayList<>();
                    cartons.put(cartonNbr, carton);
                }
                carton.add(articles[k]);
            }

            StringBuilder sb = new StringBuilder();
            for(int k=0; k<cartons.size(); ++k) {
                List<Integer> carton = cartons.get(k);
                if(carton != null) {
                    for(int article : carton) {
                        sb.append(article);
                    }
                }
                if(k < cartons.size() - 1) {
                    sb.append(Constantes.SEPARATEUR);
                }
            }
            return sb.toString();
        }

    }

}
