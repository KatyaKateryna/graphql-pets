package com.softserve.graphqlpets.dto;

import lombok.Data;

@Data
public class Dog implements Pet {
    private final Integer id;
    private final String name;
    private final String breed;
}
