package com.xspeedit.robot;

import com.xspeedit.robot.algorithmes.AlgorithmeGlouton;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.vavr.API.Seq;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class RobotTest {

    private final Robot robot = Robot.of("Robot actuel", new AlgorithmeGlouton());

    @Test
    void should_throw_illegalargumentexception_when_articles_is_not_defined() {
        // When
        val throwable = catchThrowable(() -> robot.pack(null));

        // Then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("articles is marked non-null but is null");
    }

    @Test
    void bad_argument_test() {
        // Given
        val articles = "1ssa9";

        // When
        val errorsOrSolution = robot.pack(articles);

        // Then
        assertThat(errorsOrSolution).containsInvalid(
                Seq(1, 2, 3)
                        .map(i -> "Error at position " + i)
        );
    }

    @Test
    void should_get_a_solution() {
        // Given
        val articles = "163841689525773";

        // When
        val errorsOrSolution = robot.pack(articles);

        // Then
        assertThat(errorsOrSolution).isValid();
        assertThat(errorsOrSolution.get().getPacking().asString()).isEqualTo("163/8/41/6/8/9/52/5/7/73");
    }

}
