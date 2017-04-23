package com.xspeedit.robot.domain;

import com.xspeedit.robot.error.Error;
import com.xspeedit.robot.error.Error.ArticleOversize;
import io.vavr.collection.List;
import io.vavr.control.Validation;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import java.util.UUID;

import static com.xspeedit.robot.Constants.CAPACITY_MAX;
import static io.vavr.API.List;
import static io.vavr.control.Option.when;
import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bin {
    @Include
    UUID uuid;

    int capacity;

    @With(PRIVATE)
    List<Article> articles;

    public Bin() {
        uuid = randomUUID();
        capacity = CAPACITY_MAX;
        articles = List();
    }

    public Validation<Error, Bin> add(final Article article) {
        return when(
                article.getSize() + size() <= capacity,
                withArticles(articles.append(article))
        )
                .toValidation(() -> ArticleOversize.of(article, this));
    }

    public String asString() {
        return articles.map(Article::getSize).mkString();
    }

    private int size() {
        return articles
                .map(Article::getSize)
                .sum()
                .intValue();
    }
}
