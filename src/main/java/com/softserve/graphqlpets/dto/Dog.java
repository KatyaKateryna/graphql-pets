package com.softserve.graphqlpets.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class Dog implements Pet {
    private final UUID id;
    private final String name;
    private final String breed;
}
