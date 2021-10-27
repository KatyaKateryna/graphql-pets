package com.softserve.graphqlpets.service;

import com.softserve.graphqlpets.dto.Passport;

import java.util.UUID;

public interface PassportService {
    Passport getPassport(UUID catId, boolean fastLoad);
}