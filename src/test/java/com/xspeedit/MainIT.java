package com.xspeedit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.xspeedit.Main.main;
import static com.xspeedit.TestAppender.clear;
import static com.xspeedit.TestAppender.messages;
import static org.assertj.core.api.Assertions.assertThat;

class MainIT {

    @BeforeEach
    void setUp() {
        clear();
    }

    @Test
    void should_execute() {
        // When
        main(new String[]{});

        // Then
        assertThat(messages)
                .isEqualTo(
                        List.of(
                                "Articles : 163841689525773",
                                "Robot actuel : 163/8/41/6/8/9/52/5/7/73 => 10 cartons utilisés",
                                "Robot optimisé : 163/81/46/82/9/55/73/7 => 8 cartons utilisés"

                        )
                );
    }

}