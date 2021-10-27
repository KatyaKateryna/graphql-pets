package com.softserve.graphqlpets.service;

import com.softserve.graphqlpets.dto.Passport;

public interface PassportService {
    Passport getPassport(Integer catId, boolean fastLoad);
}