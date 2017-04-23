package com.xspeedit.robot.algorithmes;

import com.xspeedit.robot.domain.Article;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.vavr.API.List;
import static org.assertj.core.api.Assertions.assertThat;

class AlgorithmeGloutonTest {

    private final IAlgorithme algorithme = new AlgorithmeGlouton();

    @Test
    void should_resolve_problem() {
        // Given
        val articles = List(1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3).map(Article::of);

        // When
        val result = algorithme.solve(articles);

        // Then
        assertThat(result.asString()).isEqualTo("163/8/41/6/8/9/52/5/7/73");
    }

}
