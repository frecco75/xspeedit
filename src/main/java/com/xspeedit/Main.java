package com.xspeedit;


import com.xspeedit.robot.Robot;
import com.xspeedit.robot.algorithmes.AlgorithmeGlouton;
import com.xspeedit.robot.algorithmes.AlgorithmeOptimise;

public class Main {

    public static void main(final String[] args) {
        String articles = "163841689525773"; // cas par défaut
        if(args.length == 1) {
            articles = args[0];
        }

        Robot robotActuel = new Robot(new AlgorithmeGlouton());
        Robot robotOptimise = new Robot(new AlgorithmeOptimise());

        String solutionActuelle = robotActuel.emballe(articles);
        String solutionOptimisee = robotOptimise.emballe(articles);

        System.out.println("Articles      : " + articles);
        System.out.println("Robot actuel  : " + solutionActuelle + " => " + solutionActuelle.split("/").length + " cartons utilisés");
        System.out.println("Robot optimisé: " + solutionOptimisee + " => " + solutionOptimisee.split("/").length + " cartons utilisés");
    }

}
