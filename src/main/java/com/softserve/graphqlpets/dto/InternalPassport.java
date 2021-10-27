package com.softserve.graphqlpets.dto;

import lombok.Data;

@Data
public class InternalPassport implements Passport {
    private final String birthDate;
}
