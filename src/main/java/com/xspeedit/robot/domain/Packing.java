package com.xspeedit.robot.domain;

import com.xspeedit.robot.error.Error;
import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import static com.xspeedit.robot.Constants.SEPARATOR;
import static io.vavr.API.List;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE, staticName = "of")
@Slf4j
public class Packing {

    List<Bin> bins;

    public Packing() {
        bins = List();
    }

    public Packing newBin(final Article article) {
        return Packing.of(new Bin()
                .add(article)
                .fold(
                        err -> {
                            log.error(err.text());
                            return bins;
                        },
                        bins::append
                ));
    }

    public Packing best(final Packing other) {
        return getNbrElements() != other.getNbrElements() && getNbrElements() > other.getNbrElements()
                || bins.size() <= other.bins.size() ?
                this : other;
    }

    public Validation<Error, Packing> add(final Bin bin, final Article article) {
        return bin
                .add(article)
                .map(this::replace);
    }

    public String asString() {
        return bins.map(Bin::asString).mkString(SEPARATOR);
    }

    private Packing replace(final Bin bin) {
        return Packing.of(
                bins.replace(bin, bin)
        );
    }

    public int getNbrElements() {
        return bins.map(Bin::getArticles)
                .map(Traversable::size)
                .sum()
                .intValue();
    }

}
