package com.xspeedit.robot.domain;

import com.xspeedit.robot.error.Error.ArticleOversize;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.vavr.API.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class BinTest {

    @Test
    void should_add_article() {
        // Given
        val bin = new Bin();
        val article = Article.of(8);

        // When
        val errorOrBin = bin.add(article);

        // Then
        assertThat(errorOrBin).isValid();
        assertThat(errorOrBin.get()).satisfies(bin1 -> {
            assertThat(bin1.getCapacity()).isEqualTo(bin.getCapacity());
            assertThat(bin1.getUuid()).isEqualTo(bin.getUuid());
            assertThat(bin1.getArticles()).isEqualTo(List(article));
        });
    }

    @Test
    void should_not_add_article_greater_than_max_capacity() {
        // Given
        val bin = new Bin();
        val article = Article.of(11);

        // When
        val errorOrBin = bin.add(article);

        // Then
        assertThat(errorOrBin).containsInvalid(ArticleOversize.of(article, bin));
    }

    @Test
    void should_not_add_article_when_all_sizes_exceed_capacity() {
        // Given
        val bin = new Bin();
        val article9 = Article.of(9);
        val article2 = Article.of(2);

        // When
        val errorOrBin = bin.add(article9).flatMap(c -> c.add(article2));

        // Then
        assertThat(errorOrBin).containsInvalid(ArticleOversize.of(article2, bin));
    }

    @Test
    void should_describe_articles() {
        // Given
        val article1 = Article.of(1);
        val article9 = Article.of(9);
        val bin = new Bin().add(article9).flatMap(c -> c.add(article1)).get();

        // When
        val asString = bin.asString();

        // Then
        assertThat(asString).isEqualTo("91");
    }
}