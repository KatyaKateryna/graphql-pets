package com.softserve.graphqlpets.service.impl;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.CatInput;
import com.softserve.graphqlpets.dto.Color;
import com.softserve.graphqlpets.service.CatService;
import graphql.com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CatServiceImpl implements CatService {

    private final Map<Integer, Cat> cats = new ConcurrentHashMap<>();
    private int nextId;

    @PostConstruct
    private void setUp() {
        cats.put(1, new Cat(1, "Foo", Sets.immutableEnumSet(Color.BLACK)));
        cats.put(2, new Cat(2, "Bar", Sets.immutableEnumSet(Color.RED, Color.WHITE)));

        nextId = 3;
    }

    @Override
    public Cat findById(Integer id) {
        return cats.get(id);
    }

    @Override
    public Cat createCat(CatInput cat) {
        Cat newCat = new Cat(nextId, cat.getName(), cat.getColors() == null ? Collections.emptySet() : cat.getColors());

        cats.put(nextId++, newCat);

        return newCat;
    }
}