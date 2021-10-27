package com.softserve.graphqlpets.service;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.CatInput;

public interface CatService {
    Cat findById(Integer id);

    Cat createCat(CatInput cat);
}
