package com.softserve.graphqlpets.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class Cat implements Pet {
    private final UUID id;
    private final String name;
    private final Set<Color> colors;
}