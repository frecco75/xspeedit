package com.xspeedit.robot.error;

import com.xspeedit.robot.domain.Article;
import com.xspeedit.robot.domain.Bin;
import com.xspeedit.robot.error.Error.ArticleOversize;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorTest {

    @Test
    void should_compute_text() {
        // Given
        val article = Article.of(1);
        val bin = new Bin();
        val error = ArticleOversize.of(article, bin);

        // When
        val text = error.text();

        // Then
        assertThat(text).isEqualTo("Article " + article + " trop gros pour le bin " + bin.getUuid());
    }

}