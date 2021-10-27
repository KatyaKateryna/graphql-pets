package com.softserve.graphqlpets.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InternalPassport implements Passport {
    private final LocalDate birthDate;
}
