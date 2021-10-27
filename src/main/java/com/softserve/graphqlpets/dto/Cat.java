package com.softserve.graphqlpets.dto;

import lombok.Data;

import java.util.Set;

@Data
public class Cat implements Pet {
    private final Integer id;
    private final String name;
    private final Set<Color> colors;
}