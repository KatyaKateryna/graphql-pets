package com.softserve.graphqlpets.service;

import com.softserve.graphqlpets.dto.Passport;

import java.util.List;
import java.util.UUID;

public interface PassportService {
    Passport getPassport(UUID catId, boolean fastLoad);

    /**
     * Passports MUST be in the same order as cat ids
     */
    List<Passport> getPassports(List<UUID> catIds, boolean fastLoad);
}