package com.softserve.graphqlpets.service;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.CatInput;

import java.util.UUID;

public interface CatService {
    Cat findById(UUID id);

    Cat createCat(CatInput cat);
}
