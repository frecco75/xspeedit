package com.xspeedit.robot.domain;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Value;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Value(staticConstructor = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Article {
    @Include
    UUID uuid = randomUUID();
    int size;
}
