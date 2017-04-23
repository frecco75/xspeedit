package com.xspeedit.robot.domain;

import com.xspeedit.robot.error.Error.ArticleOversize;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.vavr.API.List;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class PackingTest {

    @Test
    void should_create_new_bin() {
        // Given
        val packing = new Packing();
        val article = Article.of(3);

        // When
        val newPacking = packing.newBin(article);

        // Then
        assertThat(newPacking.getBins()).hasSize(1);
        assertThat(newPacking.getBins().head().getArticles()).isEqualTo(List(article));
    }

    @Test
    void should_not_create_new_bin_when_size_is_greater_than_capacity() {
        // Given
        val packing = new Packing();
        val article = Article.of(11);

        // When
        val newPacking = packing.newBin(article);

        // Then
        assertThat(newPacking.getBins()).isEmpty();
    }

    @Test
    void should_add_article() {
        // Given
        val article2 = Article.of(2);
        val article3 = Article.of(3);
        val packing = new Packing().newBin(article3);
        val bin = packing.getBins().head();

        // When
        val errorOrPacking = packing.add(bin, article2);

        // Then
        assertThat(errorOrPacking).isValid();
        assertThat(errorOrPacking.get().getBins()).hasSize(1);
        assertThat(errorOrPacking.get().getBins().head().getArticles()).isEqualTo(List(article3, article2));
    }

    @Test
    void should_fail_when_trying_to_add_article() {
        // Given
        val article3 = Article.of(3);
        val article8 = Article.of(8);
        val packing = new Packing().newBin(article3);
        val bin = packing.getBins().head();

        // When
        val errorOrPacking = packing.add(bin, article8);

        // Then
        assertThat(errorOrPacking).containsInvalid(ArticleOversize.of(article8, bin));
    }

}