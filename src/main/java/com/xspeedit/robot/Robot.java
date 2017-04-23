package com.xspeedit.robot;

import com.xspeedit.robot.algorithmes.IAlgorithme;
import com.xspeedit.robot.domain.Article;
import com.xspeedit.robot.domain.Packing;
import io.vavr.API;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

import static io.vavr.control.Option.when;
import static io.vavr.control.Validation.traverse;
import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;

@Value(staticConstructor = "of")
@Slf4j
public class Robot {

    String name;
    IAlgorithme algorithme;

    public Validation<Seq<String>, Solution> pack(@NonNull final String articles) {
        return validate(articles)
                .map(algorithme::solve)
                .map(packing -> Solution.of(packing, this::print));
    }

    private Validation<Seq<String>, Iterable<Article>> validate(final String articles) {
        return traverse(
                CharSeq.of(articles).zipWithIndex(),
                t -> validateInt(t._1, t._2).mapError(API::Seq)
        )
                .map(articles1 -> articles1.map(Article::of));
    }

    private Validation<String, Integer> validateInt(final Character c, final int position) {
        return when(isDigit(c), getNumericValue(c))
                .toValidation(() -> "Error at position " + position);
    }

    private void print(final Packing packing) {
        log.info("{} : {} => {} cartons utilisés", name, packing.asString(), packing.getBins().size());
    }

    @Value(staticConstructor = "of")
    public static class Solution {
        Packing packing;
        Consumer<Packing> print;

        public Solution print() {
            print.accept(packing);
            return this;
        }
    }
}
