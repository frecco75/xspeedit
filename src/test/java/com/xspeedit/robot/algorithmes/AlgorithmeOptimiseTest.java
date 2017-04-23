package com.xspeedit.robot.algorithmes;

import com.xspeedit.robot.domain.Article;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.xspeedit.robot.Constants.SEPARATOR;
import static io.vavr.API.List;
import static org.assertj.core.api.Assertions.assertThat;

class AlgorithmeOptimiseTest {

    private final IAlgorithme algorithme = new AlgorithmeOptimise();

    @Test
    void should_resolve_problem() {
        // Given
        val articles = List(1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3).map(Article::of);
        val expected = "163/82/46/19/8/55/73/7";

        // When
        val result = algorithme
                .solve(articles)
                .asString();

        // Then
        verifierNombreBins(expected, result);
        assertThat(expected.length()).isEqualTo(result.length());
    }

    private void verifierNombreBins(final String solution1, final String solution2) {
        val nbrBinsSolution1 = solution1.split(SEPARATOR).length;
        val nbrBinsSolution2 = solution2.split(SEPARATOR).length;

        assertThat(nbrBinsSolution1).isEqualTo(nbrBinsSolution2);
    }

}
