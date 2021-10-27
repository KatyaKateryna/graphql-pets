package com.softserve.graphqlpets.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InternationalPassport implements Passport {
    private final LocalDate vaccinationDate;
}
