package com.softserve.graphqlpets.dto;

import lombok.Data;

@Data
public class InternationalPassport implements Passport {
    private final String vaccinationDate;
}
