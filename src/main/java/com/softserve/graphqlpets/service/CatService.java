package com.softserve.graphqlpets.service;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.CatInput;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface CatService {
    Optional<Cat> findById(UUID id);

    Cat createCat(CatInput cat);

    Collection<Cat> getAllCats();
}
