package com.xspeedit;


import com.xspeedit.robot.Robot;
import com.xspeedit.robot.Robot.Solution;
import com.xspeedit.robot.algorithmes.AlgorithmeGlouton;
import com.xspeedit.robot.algorithmes.AlgorithmeOptimise;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static io.vavr.control.Validation.combine;
import static java.util.function.Function.identity;

@Slf4j
public class Main {

    private static final String ARTICLES = "163841689525773";

    private final static Robot ROBOT_ACTUEL = Robot.of("Robot actuel", new AlgorithmeGlouton());
    private final static Robot ROBOT_OPTIMISE = Robot.of("Robot optimisé", new AlgorithmeOptimise());

    public static void main(final String[] args) {
        val articles = args.length == 1 ? args[0] : ARTICLES;
        log.info("Articles : {}", articles);

        combine(
                ROBOT_ACTUEL.pack(articles),
                ROBOT_OPTIMISE.pack(articles)
        )
                .ap(API::List)
                .mapError(seq -> seq.flatMap(identity()))
                .map(solutions -> solutions.map(Solution::print))
                .swap()
                .forEach(errors -> errors.forEach(log::error));
    }

}
